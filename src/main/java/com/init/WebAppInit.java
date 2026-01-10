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

		log.debug("=============== WebApplicationInitializer START ===============");

		// 1. AnnotationConfigWebApplicationContext 객체 생성
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();

		// 2. 방금 만든 AppConfig 클래스(또는 설정 클래스들)를 등록
		rootContext.register(AppConfig.class, DataSourceConfig.class);

		// 만약 설정 클래스가 여러 개라면 콤마로 구분하여 등록 가능합니다.
		// rootContext.register(AppConfig.class, DataSourceConfig.class);

		// 3. (옵션) refresh와 start는 ContextLoaderListener가 내부적으로 처리하므로
		// 일반적으로 WebApplicationInitializer 환경에서는 아래 Listener 등록만으로 충분합니다.

		// 4. ContextLoaderListener에 생성한 rootContext 전달
		sc.addListener(new ContextLoaderListener(rootContext));

		// 1. XML 대신 Annotation 기반 ApplicationContext 생성
		AnnotationConfigWebApplicationContext dispatcherContext = new AnnotationConfigWebApplicationContext();
		dispatcherContext.register(WebConfig.class); // 자바 설정 파일 등록

		// 2. DispatcherServlet 등록
		ServletRegistration.Dynamic dispatcher = sc.addServlet("dispatcher", new DispatcherServlet(dispatcherContext));

		// 3. 기존 설정 적용
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("*.do", "*.ax", "*.pg");

		loadEncodingFilter(sc);

		log.debug("=============== WebApplicationInitializer END ===============");

	}

	/**
	 * Desc : 인코딩 필터
	 *
	 * @Method Name : setEncodingFilter
	 * @param sc
	 */
	private void loadEncodingFilter(ServletContext sc) {

		log.debug("    =========== Filter: Encoding START ===========    ");

		FilterRegistration.Dynamic filter = sc.addFilter("encodingFilter",
			new org.springframework.web.filter.CharacterEncodingFilter());
		filter.setInitParameter("encoding", "UTF-8");
		filter.setInitParameter("forceEncoding", "true");
		filter.addMappingForUrlPatterns(null, false, "/");

		log.debug("    =========== Filter: Encoding END ===========    ");

	}

}