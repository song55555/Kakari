package com.gbi.kakari.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@RequestMapping("/dashboard")
	public String dashboard(Model model) {
		return "admin/dashboard";
	}

	@RequestMapping("/users")
	public String users(Model model) {
		return "admin/users";
	}

	@RequestMapping("/products")
	public String products(Model model) {
		return "admin/products";
	}

	@RequestMapping("/orders")
	public String orders(Model model) {
		return "admin/orders";
	}
}
