package com.init;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.config.AppConfig;
import com.config.DataSourceConfig;
import com.config.WebConfig;

import jakarta.servlet.FilterRegistration;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletRegistration;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WebAppInit implements WebApplicationInitializer {

	public void onStartup(ServletContext sc) {

		log.info("[WebApplicationInitializer] => START");

		log.info("    [application context] => START");
		// 1. rootContext 객체 생성
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();

		// 2. allication context clz 등록
		rootContext.register(AppConfig.class, DataSourceConfig.class);

		// 3. ContextLoaderListener에 생성한 rootContext 전달
		sc.addListener(new ContextLoaderListener(rootContext));
		log.info("    [application context] => END");

		log.info("    [web_application context] => START");
		// 1. XML 대신 Annotation 기반 ApplicationContext 생성
		AnnotationConfigWebApplicationContext dispatcherContext = new AnnotationConfigWebApplicationContext();
		dispatcherContext.register(WebConfig.class); // 자바 설정 파일 등록

		// 2. DispatcherServlet 등록
		ServletRegistration.Dynamic dispatcher = sc.addServlet("dispatcher", new DispatcherServlet(dispatcherContext));

		// 3. 기존 설정 적용
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("*.do", "*.ax", "*.pg");
		log.info("    [web_application context] => END");

		log.info("    [Filter encoding] => START");
		// 1. encoding filter
		loadEncodingFilter(sc);
		log.info("    [Filter encoding] => END");

		log.info("[WebApplicationInitializer] => END");

	}

	private void loadEncodingFilter(ServletContext sc) {
		FilterRegistration.Dynamic filter = sc.addFilter("encodingFilter", new org.springframework.web.filter.CharacterEncodingFilter());
		filter.setInitParameter("encoding", "UTF-8");
		filter.setInitParameter("forceEncoding", "true");
		filter.addMappingForUrlPatterns(null, false, "/");
	}

}