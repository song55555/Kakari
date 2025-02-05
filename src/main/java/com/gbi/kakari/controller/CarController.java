package com.gbi.kakari.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gbi.kakari.entity.car.Product;
import com.gbi.kakari.entity.car.ProductColor;
import com.gbi.kakari.service.ProductService;

@Controller
@RequestMapping("/car")
public class CarController {
	private final ProductService productService;

	@Autowired
	public CarController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping("/list")
	public String carList(Model model) {
		List<Product> products = productService.getAllProducts();
		model.addAttribute("products", products);
		return "car/list";
	}

	@GetMapping("/list/{productId}")
	public String carDetail(@PathVariable Integer productId, Model model) {
		Product product = productService.getProductById(productId);
		List<ProductColor> colors = productService.getColorsByProductId(productId);
		model.addAttribute("product", product);
		model.addAttribute("colors", colors);
		return "car/detail";
	}
}
