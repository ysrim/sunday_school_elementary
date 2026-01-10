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
@PropertySource("classpath:spring/prop/globals.properties") // 공통 프로퍼티 소스
public class AppConfig {

	@Bean(name = "globalsProps")
	public PropertiesFactoryBean globalsProps() {
		PropertiesFactoryBean bean = new PropertiesFactoryBean();
		bean.setLocation(new ClassPathResource("spring/prop/globals.properties"));
		return bean;
	}

	// 참고: PropertySourcesPlaceholderConfigurer는 DataSourceConfig에도 존재하지만,
	// Context가 로드될 때 병합되어 처리되거나 각 설정 파일 범위에서 동작합니다.
	// 만약 AppConfig에서도 @Value를 쓴다면 여기에 static 빈을 추가해야 합니다.
}