package com.config;

import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Configuration
@ComponentScan(
	basePackages = {"com", "net", "app"},
	useDefaultFilters = false,
	includeFilters = {
		@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Service.class),
		@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Repository.class)
	}
)
@PropertySource("classpath:spring/prop/globals.properties")
public class AppConfig {

	@Bean(name = "globalsProps")
	public PropertiesFactoryBean globalsProps() {
		PropertiesFactoryBean bean = new PropertiesFactoryBean();
		bean.setLocation(new ClassPathResource("spring/prop/globals.properties"));
		return bean;
	}

}