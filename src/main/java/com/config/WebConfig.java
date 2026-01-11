package com.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

import com.base.interceptor.LoginInterceptor;
import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com", "net", "app"}, useDefaultFilters = false, includeFilters = {
		@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Controller.class, ControllerAdvice.class})})
public class WebConfig implements WebMvcConfigurer, ApplicationContextAware {

	private ApplicationContext applicationContext;

	@Autowired
	private LoginInterceptor loginInterceptor;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	/* --- [Start] Thymeleaf 설정 --- */
	@Bean
	public SpringResourceTemplateResolver templateResolver() {
		SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
		templateResolver.setApplicationContext(this.applicationContext);
		templateResolver.setPrefix("/WEB-INF/view/templates/"); // HTML 파일 위치
		templateResolver.setSuffix(".html");
		templateResolver.setTemplateMode(TemplateMode.HTML);
		templateResolver.setCacheable(false);
		templateResolver.setCharacterEncoding("UTF-8");
		return templateResolver;
	}

	@Bean
	public SpringTemplateEngine templateEngine() {
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(templateResolver());
		templateEngine.setEnableSpringELCompiler(true);
		templateEngine.addDialect(new LayoutDialect());
		return templateEngine;
	}

	@Bean
	public ThymeleafViewResolver thymeleafViewResolver() {
		ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
		viewResolver.setTemplateEngine(templateEngine());
		viewResolver.setCharacterEncoding("UTF-8");
		viewResolver.setOrder(1);
		viewResolver.setViewNames(new String[]{"page/*"}); // "page/"로 시작하는 것만 처리하도록 제한

		return viewResolver;
	}
	/* --- [End] Thymeleaf 설정 --- */

	/**
	 * BeanNameViewResolver 설정 (Order: 0)
	 * 가장 먼저 실행되어 빈 이름과 일치하는 뷰가 있는지 확인
	 */
	@Bean
	public BeanNameViewResolver beanNameViewResolver() {
		BeanNameViewResolver resolver = new BeanNameViewResolver();
		resolver.setOrder(0);
		return resolver;
	}

	/**
	 * ViewResolver 등록
	 * 논리적 순서:
	 * 1. BeanNameViewResolver (Order 0)
	 * 2. ThymeleafViewResolver (Order 1) -> "th/*" 패턴일 때만 인터셉트
	 * 3. InternalResourceViewResolver (Order 2) -> 나머지 모든 요청(JSP) 처리 (Default)
	 */
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		// 1. Thymeleaf 등록 (조건부 실행)
		registry.viewResolver(thymeleafViewResolver());
		// 2. JSP 등록 (기본 실행 - Fallback)
		InternalResourceViewResolver jspResolver = new InternalResourceViewResolver();
		jspResolver.setViewClass(JstlView.class);
		jspResolver.setPrefix("/WEB-INF/view/");
		jspResolver.setSuffix(".jsp");
		jspResolver.setOrder(2); // Thymeleaf가 처리 안 한 것은 모두 JSP가 처리
		registry.viewResolver(jspResolver);
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(loginInterceptor)
				.addPathPatterns("/**")            // 모든 경로에 적용
				.excludePathPatterns("/intro/**", "/fiels/**"); // 특정 경로는 제외
	}

}