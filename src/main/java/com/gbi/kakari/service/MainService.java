package com.gbi.kakari.service;

import org.springframework.stereotype.Service;

@Service
public class MainService {
	private final String title = "KAKARI";

	public String getTitle() {
		return title;
	}
}
