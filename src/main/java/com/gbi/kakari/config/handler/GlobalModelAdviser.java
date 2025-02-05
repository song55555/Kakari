package com.gbi.kakari.config.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.gbi.kakari.service.AuthService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class GlobalModelAdviser {
	private final AuthService authService;

	public GlobalModelAdviser(AuthService authService) {
		this.authService = authService;
	}

	// HttpServletRequest, HttpServletResponse 등 여러 Bean 객체를 매개변수로 받을 수 있음
	@ModelAttribute("isLoggedIn")
	public boolean addModel_isLoggedIn(HttpServletRequest request) {
		if (request.getMethod().equalsIgnoreCase("POST")) {
			log.debug("POST Request, Skipping Add isLoggedIn Model Attribute...");
			return false;
		}
		HttpSession session = request.getSession();
		if (!authService.isLoggedIn(session)) {
			log.debug("User is not logged in, Skipping Add isLoggedIn Model Attribute...");
			return false;
		}
		log.debug("Adding isLoggedIn Model Attribute...");
		return true;
	}

	/*@ModelAttribute("isAdmin")
	public boolean addModel_isAdmin(HttpSession session) {
		return authService.isAdminLoggedIn(session);
	}*/
}
