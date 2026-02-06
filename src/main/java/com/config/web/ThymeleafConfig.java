package com.config.web;

import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

@Configuration
public class ThymeleafConfig {

    private static final String RESOLVER_PATH = "/WEB-INF/view/templates";

    @Bean
    public SpringResourceTemplateResolver studentTemplateResolver() {
        return createTemplateResolver(RESOLVER_PATH + "/std", 1);
    }

    @Bean
    public SpringResourceTemplateResolver teacherTemplateResolver() {
        return createTemplateResolver(RESOLVER_PATH + "/tch", 2);
    }

    @Bean
    public SpringResourceTemplateResolver adminTemplateResolver() {
        return createTemplateResolver(RESOLVER_PATH + "/adm", 3);
    }

    @Bean
    public SpringResourceTemplateResolver layoutTemplateResolver() {
        return createTemplateResolver(RESOLVER_PATH, 4);
    }

    private SpringResourceTemplateResolver createTemplateResolver(String prefix, int order) {
        SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
        resolver.setPrefix(prefix);
        resolver.setSuffix(".html");
        resolver.setTemplateMode(TemplateMode.HTML);
        resolver.setOrder(order);
        resolver.setCheckExistence(true);
        resolver.setCacheable(false); // 운영 시 true로 변경
        return resolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        // 필요한 리졸버 모두 추가
        templateEngine.addTemplateResolver(studentTemplateResolver()); // 학생
        templateEngine.addTemplateResolver(teacherTemplateResolver()); // 선생
        templateEngine.addTemplateResolver(adminTemplateResolver()); // 관리자
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