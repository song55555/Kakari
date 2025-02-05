package com.gbi.kakari.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.gbi.kakari.config.interceptor.AdminCheckInterceptor;
import com.gbi.kakari.config.interceptor.DuplicatedAuthInterceptor;
import com.gbi.kakari.config.interceptor.LoginCheckInterceptor;
import com.gbi.kakari.service.AuthService;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	private final AuthService authService;

	@Autowired
	public WebConfig(AuthService authService) {
		this.authService = authService;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new DuplicatedAuthInterceptor(authService))
			.addPathPatterns("/auth/login")
			.addPathPatterns("/auth/register");
		registry.addInterceptor(new AdminCheckInterceptor(authService))
			.addPathPatterns("/admin/**");
		registry.addInterceptor(new LoginCheckInterceptor(authService))
			.excludePathPatterns("/static/**")
			.excludePathPatterns("/favicon.ico")
			.excludePathPatterns("/")
			.excludePathPatterns("/error/**")
			.excludePathPatterns("/auth/**")
			.excludePathPatterns("/admin/**")
			.excludePathPatterns("/api/**")
			.excludePathPatterns("/js/**")
			.excludePathPatterns("/css/**")
			.excludePathPatterns("/images/**");
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/favicon.ico")
			.addResourceLocations("classpath:/static/favicon.ico");
		registry.addResourceHandler("/js/**")
			.addResourceLocations("classpath:/static/js/");
		registry.addResourceHandler("/css/**")
			.addResourceLocations("classpath:/static/css/");
		registry.addResourceHandler("/images/**")
			.addResourceLocations("classpath:/static/images/");
	}
}
