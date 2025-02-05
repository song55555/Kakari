package com.gbi.kakari.config.interceptor;

import java.io.IOException;

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
public class LoginCheckInterceptor implements HandlerInterceptor {
	private final AuthService authService;

	public LoginCheckInterceptor(AuthService authService) {
		this.authService = authService;
	}

	@Override
	public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler)
		throws IOException {
		HttpSession session = request.getSession();
		log.debug("{} -> Request URI: {}", this.getClass().getSimpleName(), request.getRequestURI());
		if (!authService.isLoggedIn(session)) {
			throw new AccessRestrictionException(request.getRequestURI(), "로그인이 필요합니다.");
		}
		String userId = authService.getSessionUserId(session);
		log.debug("User '{}' is logged in", userId);
		/*
		 * Request Attribute의 유효 기간 및 범위:
		 * HttpServletRequest 객체에 설정된 attribute는 해당 HTTP 요청의 처리 생명주기 동안만 유효합니다.
		 * 즉, 클라이언트의 요청이 서버에 도착하여 처리되기 시작하는 시점부터, 서버가 클라이언트에게 응답을 완료하는 시점까지 그 범위가 유지됩니다.
		 */
		request.setAttribute("userId", userId);
		return true;
	}
}
