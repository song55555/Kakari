package com.gbi.kakari.config.interceptor;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.gbi.kakari.exception.access.AccessRestrictionException;
import com.gbi.kakari.service.AuthService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AdminCheckInterceptor implements HandlerInterceptor {
	private final AuthService authService;

	public AdminCheckInterceptor(AuthService authService) {
		this.authService = authService;
	}

	@Override
	public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) {
		log.debug("{} -> Request URI: {}", this.getClass().getSimpleName(), request.getRequestURI());
		HttpSession session = request.getSession();
		if (!authService.isAdminLoggedIn(session)) {
			throw new AccessRestrictionException(request.getRequestURI(), "관리자 이외에는 접근할 수 없습니다");
		}

		request.setAttribute("adminMode", true);

		return true;
	}
}
