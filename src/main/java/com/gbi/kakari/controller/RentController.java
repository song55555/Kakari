package com.gbi.kakari.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gbi.kakari.entity.car.Product;
import com.gbi.kakari.entity.rent.RentLocation;
import com.gbi.kakari.service.RentService;

@Controller
@RequestMapping("/rent")
public class RentController {
	private final RentService rentService;

	@Autowired
	public RentController(RentService rentService) {
		this.rentService = rentService;
	}

	@GetMapping()
	public String rentPage(Model model) {
		List<RentLocation> rentLocations = rentService.getRentLocations();
		List<Product> rentCarProducts = rentService.getRentCarProducts();

		model.addAttribute("rentLocations", rentLocations);
		model.addAttribute("rentCarProducts", rentCarProducts);

		return "car/rent";
	}
}
