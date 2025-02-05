package com.gbi.kakari.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gbi.kakari.dto.request.AuthRequest;
import com.gbi.kakari.dto.request.UserRequest;
import com.gbi.kakari.service.AuthService;
import com.gbi.kakari.type.user.Role;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/auth")
@Slf4j
public class AuthController {
	private final AuthService authService;

	@Autowired
	public AuthController(AuthService authService) {
		this.authService = authService;
	}

	@GetMapping("/login")
	public String login() {
		return "auth/login";
	}

	@GetMapping("/register")
	public String register() {
		return "auth/register";
	}

	@PostMapping("/login")
	public String loginPost(AuthRequest authRequest, HttpSession session, RedirectAttributes redirectAttributes) {
		log.debug("Logging in: {}", authRequest);
		if (!authService.login(authRequest.toDTO(), session)) {
			log.debug("Invalid credentials");
			redirectAttributes.addFlashAttribute("error", "Invalid credentials");
			return "redirect:/auth/login";
		}

		if ((int)session.getAttribute("userRole") == Role.ADMIN.getValue()) {
			log.debug("Admin logged in");
			return "redirect:/admin/dashboard";
		}

		return "redirect:/";
	}

	@PostMapping("/register")
	public String registerUser(UserRequest userRequest) {
		log.debug("Registering user: {}", userRequest);

		authService.register(userRequest.toDTO(), Role.USER);

		return "redirect:/auth/login";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		authService.logout(session);
		return "redirect:/";
	}
}
