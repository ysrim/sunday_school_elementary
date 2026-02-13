package com.config;

import com.config.context.CacheConfig;
import com.config.context.DataSourceConfig;
import com.config.context.SchedulerConfig;
import com.config.context.SecurityConfig;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Configuration
@Import({DataSourceConfig.class // db conn
		, SecurityConfig.class // password encode
		, CacheConfig.class // chache
		, SchedulerConfig.class // scheduler
})
@ComponentScan(basePackages = {"com", "app"}//
		, useDefaultFilters = false //
		, includeFilters = { //
		@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Service.class) //
		, @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Repository.class) //
		, @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Component.class) //
})
@PropertySource("classpath:spring/prop/globals.properties")
public class AppConfig {

	@Bean(name = "globalsProps")
	public PropertiesFactoryBean globalsProps() {

		PropertiesFactoryBean bean = new PropertiesFactoryBean();
		bean.setLocation(new ClassPathResource("spring/prop/globals.properties"));

		return bean;

	}

}