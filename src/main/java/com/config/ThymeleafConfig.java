package com.config;

import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

@Configuration
public class ThymeleafConfig {

	private final String resolverPath = "/WEB-INF/view/templates/";

	// --- 템플릿 리졸버 설정 (경로별) ---
	@Bean
	public SpringResourceTemplateResolver studentTemplateResolver() {
		return createTemplateResolver(resolverPath + "student/", 1);
	}

	@Bean
	public SpringResourceTemplateResolver teacherTemplateResolver() {
		return createTemplateResolver(resolverPath + "teacher/", 2);
	}

	@Bean
	public SpringResourceTemplateResolver adminTemplateResolver() {
		return createTemplateResolver(resolverPath + "admin/", 3);
	}

	@Bean
	public SpringResourceTemplateResolver layoutTemplateResolver() {
		return createTemplateResolver(resolverPath, 4);
	}

	// 반복되는 리졸버 설정을 위한 헬퍼 메서드
	private SpringResourceTemplateResolver createTemplateResolver(String prefix, int order) {
		SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
		resolver.setPrefix(prefix);
		resolver.setSuffix(".html");
		resolver.setTemplateMode(TemplateMode.HTML);
		resolver.setOrder(order);
		resolver.setCheckExistence(true);
		resolver.setCacheable(false);
		return resolver;
	}

	// --- 엔진 및 뷰 리졸버 설정 ---
	@Bean
	public SpringTemplateEngine templateEngine() {
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.addTemplateResolver(studentTemplateResolver());
		templateEngine.addTemplateResolver(teacherTemplateResolver());
		templateEngine.addTemplateResolver(adminTemplateResolver());
		templateEngine.addTemplateResolver(layoutTemplateResolver());
		templateEngine.addDialect(new LayoutDialect());
		return templateEngine;
	}

	@Bean
	public ThymeleafViewResolver thymeleafViewResolver() {
		ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
		viewResolver.setTemplateEngine(templateEngine());
		viewResolver.setCharacterEncoding("UTF-8");
		viewResolver.setOrder(2); // JSP보다 후순위로 밀기 위해 순서를 2로 설정
		viewResolver.setExcludedViewNames(new String[]{"jsp/*"});
		return viewResolver;
	}

}