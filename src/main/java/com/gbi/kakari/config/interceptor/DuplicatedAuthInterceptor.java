package com.gbi.kakari.config.interceptor;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.gbi.kakari.service.AuthService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DuplicatedAuthInterceptor implements HandlerInterceptor {
	private final AuthService authService;

	public DuplicatedAuthInterceptor(AuthService authService) {
		this.authService = authService;
	}

	@Override
	public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) throws Exception {
		log.debug("{} -> Request URI: {}", this.getClass().getSimpleName(), request.getRequestURI());
		HttpSession session = request.getSession();
		if (session != null && authService.isLoggedIn(session)) {
			log.info("User is already logged in... Redirecting to Main Page");
			response.sendRedirect("/");
		}
		return true;
	}
}
