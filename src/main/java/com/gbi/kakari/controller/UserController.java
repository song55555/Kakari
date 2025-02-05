package com.gbi.kakari.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gbi.kakari.entity.car.UserCar;
import com.gbi.kakari.entity.car.option.UserCarOption;
import com.gbi.kakari.service.ProductService;
import com.gbi.kakari.service.UserCarService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")
public class UserController {
	private final ProductService productService;
	private final UserCarService userCarService;

	@Autowired
	public UserController(ProductService productService, UserCarService userCarService) {
		this.productService = productService;
		this.userCarService = userCarService;
	}

	@GetMapping("/carlist")
	public String userCarList(HttpServletRequest request, Model model) {
		String userId = (String)request.getAttribute("userId");

		List<UserCar> userCars = userCarService.getUserCars(userId);
		model.addAttribute("userCars", userCars);

		List<CarInfoWithOption> carInfoWithOptions = userCars.stream()
			.map(userCar -> {
				List<UserCarOption> userCarOptions = userCarService.getUserCarOptions(userCar.getUserCarId());
				List<String> optionNames = userCarOptions.stream().map(userCarOption -> {
					Integer optionTypeId = userCarOption.getProductOption().getOptionTypeId();
					return productService.getOptionTypeName(optionTypeId);
				}).collect(Collectors.toList());
				return new CarInfoWithOption(userCar, optionNames);
			}).collect(Collectors.toList());
		model.addAttribute("carInfoWithOptions", carInfoWithOptions);

		return "user/car/list";
	}

	static class CarInfoWithOption {
		private final UserCar userCar;
		private final List<String> optionNames;

		public CarInfoWithOption(UserCar userCar, List<String> optionNames) {
			this.userCar = userCar;
			this.optionNames = optionNames;
		}

		public UserCar getUserCar() {
			return userCar;
		}

		public List<String> getOptionNames() {
			return optionNames;
		}
	}
}
