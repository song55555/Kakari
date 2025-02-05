package com.gbi.kakari.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.gbi.kakari.dto.ErrorViewDTO;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
// /error 를 사용자 정의하기 위해서는 Spring Boot 의 ErrorController interface 를 구현해야 함
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {
	@GetMapping("/error")
	public String error(HttpServletRequest request, @ModelAttribute("error") ErrorViewDTO errorViewDTO, Model model) {
		if (errorViewDTO != null) {
			model.addAttribute("httpStatus", errorViewDTO.getHttpStatus());
			model.addAttribute("message", errorViewDTO.getMessage());
			model.addAttribute("reason", errorViewDTO.getReason());
			model.addAttribute("requestUrl", errorViewDTO.getRequestUrl());
		}
		return "error";
	}
}
