package com.gbi.kakari.dto;

import java.io.Serial;
import java.io.Serializable;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorViewDTO implements Serializable {
	@Serial private static final long serialVersionUID = 1L;

	private String message;
	private String reason;
	private String requestUrl;
	private HttpStatus httpStatus;
}
