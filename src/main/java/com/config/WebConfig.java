package com.config;

import com.base.interceptor.LoginInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;

@Configuration
@EnableWebMvc
@Import({ThymeleafConfig.class, LoginInterceptor.class}) // Thymeleaf,LoginInterceptor 설정 로드
@ComponentScan(
		basePackages = {"com", "net", "app"},
		useDefaultFilters = false,
		includeFilters = {
				@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Controller.class, ControllerAdvice.class})
		}
)
@RequiredArgsConstructor // 생성자 주입을 위해 사용 (Lombok이 없다면 @Autowired 필드 주입 유지)
public class WebConfig implements WebMvcConfigurer {

	private final LoginInterceptor loginInterceptor;
	private final ThymeleafViewResolver thymeleafViewResolver;

	// 0순위: BeanNameViewResolver (파일 다운로드, 엑셀 뷰 등 특수 목적)
	@Bean
	public BeanNameViewResolver beanNameViewResolver() {
		BeanNameViewResolver resolver = new BeanNameViewResolver();
		resolver.setOrder(0);
		return resolver;
	}

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		// 1순위: JSP 설정
		InternalResourceViewResolver jspResolver = new InternalResourceViewResolver();
		jspResolver.setViewClass(JstlView.class);
		jspResolver.setPrefix("/WEB-INF/view/");
		jspResolver.setSuffix(".jsp");
		jspResolver.setOrder(1); // ★ Thymeleaf보다 먼저 실행
		registry.viewResolver(jspResolver);

		// 2순위: Thymeleaf 설정 (ThymeleafConfig에서 Order 2로 설정됨)
		registry.viewResolver(thymeleafViewResolver);
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(loginInterceptor)
				.addPathPatterns("/**")
				.excludePathPatterns("/intro/**", "/files/**");
	}
}