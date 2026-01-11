package com.config;

import com.base.interceptor.AuthInterceptor;

import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;

@Configuration
@EnableWebMvc
@Import({ThymeleafConfig.class, AuthInterceptor.class})
@ComponentScan(
	basePackages = {"com", "net", "app"},
	useDefaultFilters = false,
	includeFilters = {
		@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Controller.class, ControllerAdvice.class})
	}
)
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

	private final AuthInterceptor loginInterceptor;
	private final ThymeleafViewResolver thymeleafViewResolver;

	// 0순위: 파일 다운로드 등
	@Bean
	public BeanNameViewResolver beanNameViewResolver() {
		BeanNameViewResolver resolver = new BeanNameViewResolver();
		resolver.setOrder(0);
		return resolver;
	}

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		// 1. Thymeleaf 등록 (설정파일에서 Order 2, exclude jsp/* 설정됨)
		registry.viewResolver(thymeleafViewResolver);

		// 2. JSP 등록
		InternalResourceViewResolver jspResolver = new InternalResourceViewResolver();
		jspResolver.setViewClass(JstlView.class);
		jspResolver.setPrefix("/WEB-INF/view/");
		jspResolver.setSuffix(".jsp");
		jspResolver.setOrder(1);

		// ★ 핵심: 컨트롤러가 "jsp/..."로 리턴할 때만 JSP 리졸버가 작동하도록 제한
		// 이렇게 해야 JSP가 없을 때 에러가 나지 않고 안전하게 처리됨
		jspResolver.setViewNames("jsp/*");

		registry.viewResolver(jspResolver);
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(loginInterceptor)
			.addPathPatterns("/**")
			.excludePathPatterns("/idx/**", "/files/**"); // 정적 리소스와 인덱스 페이지 제외 추가 권장
	}
}