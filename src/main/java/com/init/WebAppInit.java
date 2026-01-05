package com.init;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import jakarta.servlet.FilterRegistration;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletRegistration;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WebAppInit implements WebApplicationInitializer {

	public void onStartup(ServletContext sc) {

		log.debug("=============== WebApplicationInitializer START ===============");

		XmlWebApplicationContext rootContext = new XmlWebApplicationContext();
		rootContext.setConfigLocations(new String[] {"classpath*:spring/context/context-*.xml"});
		rootContext.refresh();
		rootContext.start();
		sc.addListener(new ContextLoaderListener(rootContext));

		XmlWebApplicationContext dispatcherServlet = new XmlWebApplicationContext();
		dispatcherServlet.setConfigLocation("/WEB-INF/config/spring-com-servlet.xml");
		ServletRegistration.Dynamic dispatcher = sc.addServlet("dispatcher", new DispatcherServlet(dispatcherServlet));
		dispatcher.addMapping("*.do", "*.ax", "*.pg");
		dispatcher.setLoadOnStartup(1);

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