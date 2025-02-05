package com.gbi.kakari.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gbi.kakari.dto.TradeBoardDTO;
import com.gbi.kakari.dto.TradeBoardDetailDTO;
import com.gbi.kakari.dto.TradeBoardRequestDTO;
import com.gbi.kakari.entity.car.UserCar;
import com.gbi.kakari.exception.access.AccessRestrictionException;
import com.gbi.kakari.service.AuthService;
import com.gbi.kakari.service.TradeService;
import com.gbi.kakari.service.UserCarService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/trade")
public class TradeController {

	private final TradeService tradeService;
	private final AuthService authService;
	private final UserCarService userCarService; // UserCarService DI 추가

	@Autowired
	public TradeController(TradeService tradeService, AuthService authService, UserCarService userCarService) {
		this.tradeService = tradeService;
		this.authService = authService;
		this.userCarService = userCarService; // UserCarService 할당
	}

	// 중고거래 게시글 목록 페이지 (페이지네이션 및 검색)
	@GetMapping({"/list", "/list/{page}"}) // URL 매핑 간결화
	public String tradeList(Model model,
		@PageableDefault(size = 10, sort = "usedCreatedDate", direction = Sort.Direction.DESC) Pageable pageable,
		@PathVariable(value = "page", required = false) Integer page, // 페이지 번호 PathVariable 추가
		@RequestParam(value = "keyword", required = false) String keyword,
		@RequestParam(value = "searchType", required = false, defaultValue = "titleContent") String searchType) { // defaultValue 명시

		log.debug("tradeList() 호출, pageable: {}, page: {}, keyword: {}, searchType: {}", pageable, page, keyword, searchType);

		Pageable adjustedPageable = adjustPageable(pageable, page); // 페이지 조정 메서드 분리
		Page<TradeBoardDTO> tradeBoardPage = getTradeBoardPage(adjustedPageable, keyword, searchType); // 게시글 페이지 조회 메서드 분리

		model.addAttribute("tradeBoardPage", tradeBoardPage);
		model.addAttribute("currentPage", adjustedPageable.getPageNumber());
		model.addAttribute("pageSize", adjustedPageable.getPageSize());
		model.addAttribute("keyword", keyword);
		model.addAttribute("searchType", searchType);

		return "trade/list";
	}

	// Pageable 객체 조정 메서드 (페이지 번호 1부터 시작하도록)
	private Pageable adjustPageable(Pageable pageable, Integer page) {
		if (page != null && page > 0) {
			return Pageable.ofSize(pageable.getPageSize()).withPage(page - 1); // 페이지 번호 1 감소
		}
		return pageable;
	}

	// 게시글 페이지 조회 메서드 (검색 여부에 따라 다른 서비스 메서드 호출)
	private Page<TradeBoardDTO> getTradeBoardPage(Pageable pageable, String keyword, String searchType) {
		if (keyword != null && !keyword.isEmpty()) {
			return tradeService.searchTradeBoardList(pageable, keyword, searchType);
		} else {
			return tradeService.getTradeBoardList(pageable);
		}
	}

	// 중고거래 게시글 상세 페이지
	@GetMapping("/item/{tradePostId}")
	public String tradeItem(@PathVariable Integer tradePostId, Model model) {
		log.debug("tradeItem() 호출, tradePostId: {}", tradePostId);
		TradeBoardDetailDTO tradeBoardDetailDTO = tradeService.getTradeBoardDetail(tradePostId);
		model.addAttribute("tradeBoardDetail", tradeBoardDetailDTO);
		return "trade/item";
	}

	// 중고거래 게시글 작성 페이지
	@GetMapping("/write")
	public String tradeWriteForm(Model model, HttpServletRequest request) {
		log.debug("tradeWriteForm() 호출");
		String userId = getUserIdFromRequest(request); // 사용자 ID 추출 메서드 분리
		List<UserCar> userCars = userCarService.getUserCars(userId); // UserCar 목록 조회
		model.addAttribute("userCars", userCars); // Model 에 UserCar 목록 추가
		return "trade/write";
	}

	// 중고거래 게시글 작성 처리
	@PostMapping("/write")
	public String tradeWrite(TradeBoardRequestDTO requestDTO, HttpServletRequest request) {
		log.debug("tradeWrite() 호출, requestDTO: {}", requestDTO);
		String userId = getUserIdFromRequest(request); // 사용자 ID 추출 메서드 분리
		Integer tradePostId = tradeService.createTradeBoard(requestDTO, userId);
		return "redirect:/trade/item/" + tradePostId; // 작성된 게시글 상세 페이지로 리다이렉트
	}

	// 중고거래 게시글 수정 페이지
	@GetMapping("/edit/{tradePostId}")
	public String tradeEditForm(@PathVariable Integer tradePostId, Model model, HttpServletRequest request) {
		log.debug("tradeEditForm() 호출, tradePostId: {}", tradePostId);
		String userId = getUserIdFromRequest(request); // 사용자 ID 추출 메서드 분리
		TradeBoardDetailDTO tradeBoardDetailDTO = tradeService.getTradeBoardDetail(tradePostId);
		if (!isPostAuthor(userId, tradeBoardDetailDTO)) { // 게시글 작성자 확인 메서드 분리
			throw new AccessRestrictionException(request.getRequestURI(), "게시글 작성자만 수정할 수 있습니다");
		}
		List<UserCar> userCars = userCarService.getUserCars(userId); // UserCar 목록 조회
		model.addAttribute("userCars", userCars); // Model 에 UserCar 목록 추가
		model.addAttribute("tradeBoardDetail", tradeBoardDetailDTO);
		return "trade/edit";
	}

	// 중고거래 게시글 수정 처리
	@PostMapping("/edit/{tradePostId}")
	public String tradeEdit(TradeBoardRequestDTO requestDTO, @PathVariable Integer tradePostId, HttpServletRequest request) {
		log.debug("tradeEdit() 호출, requestDTO: {}, tradePostId: {}", requestDTO, tradePostId);
		String userId = getUserIdFromRequest(request); // 사용자 ID 추출 메서드 분리
		if (!isPostAuthor(userId, tradeService.getTradeBoardDetail(tradePostId))) { // 게시글 작성자 확인 메서드 분리 (수정 권한 재확인)
			throw new AccessRestrictionException(request.getRequestURI(), "게시글 작성자만 수정할 수 있습니다");
		}
		tradeService.updateTradeBoard(requestDTO, tradePostId, userId);
		return "redirect:/trade/item/" + tradePostId; // 수정된 게시글 상세 페이지로 리다이렉트
	}

	// 중고거래 게시글 삭제 처리
	@PostMapping("/delete/{tradePostId}")
	public String tradeDelete(@PathVariable Integer tradePostId, HttpServletRequest request) {
		log.debug("tradeDelete() 호출, tradePostId: {}", tradePostId);
		String userId = getUserIdFromRequest(request); // 사용자 ID 추출 메서드 분리
		if (!isPostAuthor(userId, tradeService.getTradeBoardDetail(tradePostId))) { // 게시글 작성자 확인 메서드 분리 (삭제 권한 확인)
			throw new AccessRestrictionException(request.getRequestURI(), "게시글 작성자만 삭제할 수 있습니다");
		}
		tradeService.deleteTradeBoard(tradePostId, userId);
		return "redirect:/trade/list"; // 게시글 목록 페이지로 리다이렉트
	}

	// 구매 페이지로 이동
	@GetMapping("/purchase/{tradePostId}")
	public String tradePurchaseForm(@PathVariable Integer tradePostId, Model model) {
		log.debug("tradePurchaseForm() 호출, tradePostId: {}", tradePostId);
		TradeBoardDetailDTO tradeBoardDetailDTO = tradeService.getTradeBoardDetail(tradePostId);
		model.addAttribute("tradeBoardDetail", tradeBoardDetailDTO);
		return "purchase/trade"; // 구매 확인 페이지 (purchase/trade.jsp)
	}

	// HttpServletRequest 에서 userId 추출 메서드
	private String getUserIdFromRequest(HttpServletRequest request) {
		return (String)request.getAttribute("userId"); // 인터셉터에서 설정한 userId 사용
	}

	// 게시글 작성자인지 확인하는 메서드
	private boolean isPostAuthor(String userId, TradeBoardDetailDTO tradeBoardDetailDTO) {
		return tradeBoardDetailDTO != null && tradeBoardDetailDTO.getUserId().equals(userId);
	}
}