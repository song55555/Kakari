package com.gbi.kakari.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gbi.kakari.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/test")
@Slf4j
public class TestController {
	private final ProductService productService;

	@Autowired
	public TestController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping("/product")
	public String product() {
		productService.getAllProducts().forEach(prod -> {
			log.debug("Product: {}", prod);
		});
		return "success";
	}

}
