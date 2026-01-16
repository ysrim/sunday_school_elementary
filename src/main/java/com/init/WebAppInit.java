package com.init;

import com.config.AppConfig;
import com.config.DataSourceConfig;
import com.config.SecurityConfig;
import com.config.WebConfig;
import jakarta.servlet.Filter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebAppInit extends AbstractAnnotationConfigDispatcherServletInitializer {

	// Root Context (Service, Repository, DB 등)
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[]{AppConfig.class, DataSourceConfig.class, SecurityConfig.class};
	}

	// Servlet Context (Controller, ViewResolver, Interceptor 등)
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[]{WebConfig.class};
	}

	// DispatcherServlet 매핑 패턴
	@Override
	protected String[] getServletMappings() {
		return new String[]{"*.do", "*.ax", "*.pg"};
	}

	// 인코딩 필터 등록 (web.xml의 filter 설정 대체)
	@Override
	protected Filter[] getServletFilters() {
		CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
		encodingFilter.setEncoding("UTF-8");
		encodingFilter.setForceEncoding(true);
		return new Filter[]{encodingFilter};
	}
}