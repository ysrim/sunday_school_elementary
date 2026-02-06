package com.config;

import com.base.interceptor.StdAuthInterceptor;
import com.base.interceptor.TchAuthInterceptor;
import com.config.web.ThymeleafConfig;

import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;

@Configuration
@EnableWebMvc
@Import({ThymeleafConfig.class // view template
	, StdAuthInterceptor.class // student interceptor
	, TchAuthInterceptor.class // student interceptor
})
@ComponentScan( //
	basePackages = {"com", "app"} //
	, useDefaultFilters = false //
	, includeFilters = { //
	@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Controller.class, ControllerAdvice.class}) //
}) //
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

	private final StdAuthInterceptor stdAuthInterceptor;

	private final TchAuthInterceptor tchAuthInterceptor;

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
		registry.viewResolver(thymeleafViewResolver);
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(stdAuthInterceptor) // 학생용 로그인
			.addPathPatterns("/std/**") // path 설정
			.excludePathPatterns("/std/idx/**"); // 인덱스 페이지(intro.pg, login.pg, join.pg) 제외
		//.excludePathPatterns("/std/idx/**", "/error/**", "/files/**"); // 정적 리소스와 인덱스 페이지 제외
		registry.addInterceptor(tchAuthInterceptor) // 학생용 로그인
			.addPathPatterns("/tch/**") // path 설정
			.excludePathPatterns("/tch/idx/**"); // 인덱스 페이지(login.pg) 제외
	}
}