package com.gbi.kakari.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gbi.kakari.entity.car.Product;
import com.gbi.kakari.entity.car.ProductColor;
import com.gbi.kakari.entity.car.option.OptionType;
import com.gbi.kakari.entity.car.option.ProductOption;
import com.gbi.kakari.service.ProductService;

@Controller
@RequestMapping("/order")
public class OrderController {
	private final ProductService productService;

	@Autowired
	public OrderController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping()
	public String orderPage(@RequestParam Integer productId, Model model) {
		Product product = productService.getProductById(productId);
		List<ProductColor> colors = productService.getColorsByProductId(productId);
		List<ProductOption> options = productService.getOptionsByProductId(productId);
		List<OptionType> optionTypes = productService.getAllOptionTypes();

		model.addAttribute("product", product);
		model.addAttribute("colors", colors);
		model.addAttribute("options", options);
		model.addAttribute("optionTypes", optionTypes);

		return "car/order";
	}
}
