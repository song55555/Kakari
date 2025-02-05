package com.gbi.kakari.config.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.gbi.kakari.dto.ErrorViewDTO;
import com.gbi.kakari.exception.ReasonableException;
import com.gbi.kakari.exception.access.AccessRestrictionException;
import com.gbi.kakari.exception.access.InvalidAccessException;
import com.gbi.kakari.exception.access.InvalidPurchaseCompleteException;

import jakarta.el.ELException;
import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class GlobalExceptionAdviser {
	@ExceptionHandler(NoHandlerFoundException.class)
	public RedirectView handleNoHandlerFoundException(NoHandlerFoundException ex, RedirectAttributes redirectAttributes) {
		log.warn("Exception Handle: {}", ex.getMessage(), ex);
		ErrorViewDTO errorViewDTO = ErrorViewDTO.builder()
			.requestUrl(ex.getRequestURL()).httpStatus(HttpStatus.NOT_FOUND).build();
		redirectAttributes.addFlashAttribute("error", errorViewDTO);
		return new RedirectView("/error");
	}

	@ExceptionHandler(AccessRestrictionException.class)
	public RedirectView handleAccessRestrictionException(AccessRestrictionException ex, RedirectAttributes redirectAttributes) {
		log.warn("Exception Handle: {}", ex.getReasonableMessage(), ex);
		ErrorViewDTO errorViewDTO = ErrorViewDTO.builder()
			.message(ex.getMessage()).reason(ex.getReason()).httpStatus(HttpStatus.FORBIDDEN).build();
		redirectAttributes.addFlashAttribute("error", errorViewDTO);
		return new RedirectView("/error");
	}

	@ExceptionHandler(InvalidAccessException.class)
	public RedirectView handleInvalidAccessException(InvalidAccessException ex, RedirectAttributes redirectAttributes) {
		log.warn("Exception Handle: {}", ex.getReasonableMessage(), ex);
		ErrorViewDTO errorViewDTO = ErrorViewDTO.builder()
			.message(ex.getMessage()).httpStatus(HttpStatus.FORBIDDEN).build();
		redirectAttributes.addFlashAttribute("error", errorViewDTO);
		return new RedirectView("/error");
	}

	@ExceptionHandler(InvalidPurchaseCompleteException.class)
	public RedirectView handleInvalidAccessException(InvalidPurchaseCompleteException ex, RedirectAttributes redirectAttributes) {
		log.warn("Exception Handle: {}", ex.getReasonableMessage(), ex);
		ErrorViewDTO errorViewDTO = ErrorViewDTO.builder()
			.message(ex.getMessage()).reason(ex.getReason()).httpStatus(HttpStatus.BAD_REQUEST).build();
		redirectAttributes.addFlashAttribute("error", errorViewDTO);
		return new RedirectView("/error");
	}

	@ExceptionHandler(ELException.class)
	public RedirectView handleReasonableException(ELException ex, RedirectAttributes redirectAttributes) {
		log.warn("Exception Handle: {}", ex.getMessage(), ex);
		ErrorViewDTO errorViewDTO = ErrorViewDTO.builder()
			.message(ex.getMessage()).httpStatus(HttpStatus.INTERNAL_SERVER_ERROR).build();
		redirectAttributes.addFlashAttribute("error", errorViewDTO);
		return new RedirectView("/error");
	}

	@ExceptionHandler(ReasonableException.class)
	public RedirectView handleReasonableException(ReasonableException ex, RedirectAttributes redirectAttributes) {
		log.warn("Exception Handle: {}", ex.getReasonableMessage(), ex);
		ErrorViewDTO errorViewDTO = ErrorViewDTO.builder()
			.message(ex.getMessage()).reason(ex.getReason()).httpStatus(HttpStatus.BAD_REQUEST).build();
		redirectAttributes.addFlashAttribute("error", errorViewDTO);
		return new RedirectView("/error");
	}

	@ExceptionHandler(Exception.class)
	public RedirectView handleDefaultException(Exception ex, RedirectAttributes redirectAttributes) {
		log.warn("Exception Handle: {}", ex.getMessage(), ex);
		ErrorViewDTO errorViewDTO = ErrorViewDTO.builder()
			.message(ex.getMessage()).httpStatus(HttpStatus.INTERNAL_SERVER_ERROR).build();
		redirectAttributes.addFlashAttribute("error", errorViewDTO);
		return new RedirectView("/error");
	}
}