package com.config.web;

import lombok.extern.slf4j.Slf4j;
import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

@Slf4j
@Configuration
@PropertySource("classpath:spring/prop/globals.properties")
public class ThymeleafConfig {

	@Value("#{'true'.equals('${is.production}') ? true : false}")
	private boolean isProduction;

	@Bean
	public SpringResourceTemplateResolver layoutTemplateResolver() {

		log.warn("isProduction: {}", isProduction);
		SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
		resolver.setPrefix("/WEB-INF/view/templates");
		resolver.setSuffix(".html");
		resolver.setTemplateMode(TemplateMode.HTML);
		resolver.setOrder(2);
		resolver.setCheckExistence(true);
		resolver.setCacheable(isProduction); // 운영 시 true로 변경

		return resolver;

	}

	@Bean
	public SpringTemplateEngine templateEngine() {

		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.addTemplateResolver(layoutTemplateResolver());
		templateEngine.addDialect(new LayoutDialect());

		return templateEngine;

	}

	@Bean
	public ThymeleafViewResolver thymeleafViewResolver() {

		ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
		viewResolver.setTemplateEngine(templateEngine());
		viewResolver.setCharacterEncoding("UTF-8");
		viewResolver.setOrder(1);

		return viewResolver;

	}

}