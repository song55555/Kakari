package com.gbi.kakari.controller;

import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gbi.kakari.dto.OrderDTO;
import com.gbi.kakari.dto.RentDTO;
import com.gbi.kakari.dto.TradeBoardDetailDTO;
import com.gbi.kakari.entity.car.Product;
import com.gbi.kakari.entity.rent.RentLocation;
import com.gbi.kakari.exception.access.InvalidPurchaseCompleteException;
import com.gbi.kakari.exception.database.ElementNotFoundException;
import com.gbi.kakari.service.PointService;
import com.gbi.kakari.service.ProductService;
import com.gbi.kakari.service.RentService;
import com.gbi.kakari.service.TradeService;
import com.gbi.kakari.service.UserCarService;
import com.gbi.kakari.type.point.PurchaseType;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/purchase")
public class PurchaseController {
	private final ProductService productService;
	private final UserCarService userCarService;
	private final RentService rentService;
	private final PointService pointService;
	private final TradeService tradeService; // TradeService DI

	@Autowired
	public PurchaseController(
		ProductService productService, UserCarService userCarService, RentService rentService,
		PointService pointService, TradeService tradeService) { // TradeService DI
		this.productService = productService;
		this.userCarService = userCarService;
		this.rentService = rentService;
		this.pointService = pointService;
		this.tradeService = tradeService; // TradeService 할당
	}

	@PostMapping("/order")
	public String orderPurchase(
		@RequestAttribute("userId") String userId, OrderDTO orderDTO, RedirectAttributes redirectAttributes) {
		Product product = productService.getProductById(orderDTO.getProductId());
		if (product == null) {
			throw new ElementNotFoundException(String.format("ProductId '%d' 에 해당하는 상품이 없습니다.", orderDTO.getProductId()));
		}

		Integer carOrderId = userCarService.orderCar(userId, product, orderDTO);
		pointService.usePoint(userId, PurchaseType.ORDER, carOrderId, product.getPrice());

		redirectAttributes.addFlashAttribute("product", product);
		return "redirect:/purchase/order/complete";
	}

	@GetMapping("/order/complete")
	public String orderComplete(@ModelAttribute("product") Product product, Model model) {
		if (product.IsNullProduct()) {
			throw new InvalidPurchaseCompleteException(); // Product 가 null 이면 예외 발생
		}
		model.addAttribute("productName", product.getProductName());
		model.addAttribute("productPrice", product.getPrice());
		model.addAttribute("productImage", product.getProductImage());
		return "purchase/order/complete";
	}

	@PostMapping("/rent")
	public String rentPurchase(
		@RequestAttribute("userId") String userId, RentDTO rentDTO, RedirectAttributes redirectAttributes) {
		Product product = productService.getProductById(rentDTO.getProductId());
		if (product == null) {
			throw new ElementNotFoundException(String.format("ProductId '%d' 에 해당하는 상품이 없습니다.", rentDTO.getProductId()));
		}

		Integer rentId = rentService.rentCar(userId, product, rentDTO);

		Long price = rentService.getPrice(product, rentDTO);
		pointService.usePoint(userId, PurchaseType.RENT, rentId, price);

		RentLocation rentLocation = rentService.getRentLocation(rentDTO.getRentLocationId());

		redirectAttributes.addFlashAttribute("product", product);
		redirectAttributes.addFlashAttribute("rentDTO", rentDTO);
		redirectAttributes.addFlashAttribute("rentLocation", rentLocation);
		redirectAttributes.addFlashAttribute("totalRentPrice", price);

		return "redirect:/purchase/rent/complete";
	}

	@GetMapping("/rent/complete")
	public String rentComplete(
		@ModelAttribute("product") Product product, @ModelAttribute("rentDTO") RentDTO rentDTO,
		@ModelAttribute("rentLocation") RentLocation rentLocation, @ModelAttribute("totalRentPrice") Long price, Model model) {

		if (product.IsNullProduct() || rentDTO.IsNullRentDTO() || rentLocation.IsNullRentLocation()) {
			throw new InvalidPurchaseCompleteException(); // 하나라도 null 이면 예외 발생
		}

		// 날짜 포맷터 생성 (원하는 형식으로 변경 가능)
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일, HH시 mm분");

		// LocalDateTime 객체를 원하는 형식의 문자열로 포맷
		String formattedStartDate = rentDTO.getRentStartDateTime().format(formatter);
		String formattedEndDate = rentDTO.getRentEndDateTime().format(formatter);

		model.addAttribute("productName", product.getProductName());
		model.addAttribute("totalRentPrice", price);
		model.addAttribute("productImage", product.getProductImage());
		model.addAttribute("rentLocationName", rentLocation.getRentLocationName());
		model.addAttribute("rentLocationAddress", rentLocation.getRentLocationAddress());
		model.addAttribute("rentLocationPhoneNum", rentLocation.getRentLocationPhoneNum());
		model.addAttribute("rentStartDate", formattedStartDate); // 포맷된 날짜 문자열 전달
		model.addAttribute("rentEndDate", formattedEndDate);   // 포맷된 날짜 문자열 전달

		return "purchase/rent/complete";
	}

	@PostMapping("/trade/{tradePostId}") // Trade 게시글 ID로 변경
	public String tradePurchase(
		@RequestAttribute("userId") String userId, @PathVariable Integer tradePostId,
		RedirectAttributes redirectAttributes) { // RedirectAttributes 사용

		tradeService.purchaseTrade(tradePostId, userId);
		TradeBoardDetailDTO tradeBoardDetailDTO = tradeService.getTradeBoardDetail(tradePostId); // TradeBoardDetailDTO 재조회
		redirectAttributes.addFlashAttribute("tradeBoardDetail", tradeBoardDetailDTO); // TradeBoardDetailDTO 를 ModelAttribute 로 전달
		return "redirect:/purchase/trade/complete";
	}

	@GetMapping("/trade/complete")
	public String tradeComplete(Model model, @ModelAttribute("tradeBoardDetail") TradeBoardDetailDTO tradeBoardDetailDTO) { // ModelAttribute 로 DTO 받기
		if (tradeBoardDetailDTO == null) { // DTO null 체크
			throw new InvalidPurchaseCompleteException();
		}
		model.addAttribute("tradeBoardDetail", tradeBoardDetailDTO); // Model 에 DTO 전달
		return "purchase/trade/complete";
	}
}
