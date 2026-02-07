package com.config;

import com.base.interceptor.MngAuthInterceptor;
import com.base.interceptor.StdAuthInterceptor;
import com.base.interceptor.TchAuthInterceptor;
import com.config.web.ThymeleafConfig;

import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;

import java.util.List;

@Configuration
@EnableWebMvc
@Import({ThymeleafConfig.class // view template
        , StdAuthInterceptor.class // student interceptor
        , TchAuthInterceptor.class // teacher interceptor
        , MngAuthInterceptor.class // manager interceptor
})
@ComponentScan( //
        basePackages = {"com", "app"} //
        , useDefaultFilters = false //
        , includeFilters = { //
        @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Controller.class, ControllerAdvice.class}) //
}) //
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final StdAuthInterceptor stdAuthInterceptor;

    private final TchAuthInterceptor tchAuthInterceptor;

    private final MngAuthInterceptor mngAuthInterceptor;

    private final ThymeleafViewResolver thymeleafViewResolver;

    // 정적 리소스 인터셉터에서 제외
    private static final List<String> STATIC_RESOURCES = List.of("/css/**", "/js/**", "/images/**", "/files/**", "/favicon.ico", "/error");

    @Bean
    public BeanNameViewResolver beanNameViewResolver() {
        BeanNameViewResolver resolver = new BeanNameViewResolver();
        resolver.setOrder(0);
        return resolver;
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.viewResolver(thymeleafViewResolver);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(stdAuthInterceptor) // 학생용 로그인
                .addPathPatterns("/std/**") // path 설정
                .excludePathPatterns("/std/idx/**") // 인덱스페이지들은 제외
                .excludePathPatterns(STATIC_RESOURCES);

        registry.addInterceptor(tchAuthInterceptor) // 선생님용 로그인
                .addPathPatterns("/tch/**") // path 설정
                .excludePathPatterns("/tch/idx/**") // 인덱스페이지들은 제외
                .excludePathPatterns(STATIC_RESOURCES);

        registry.addInterceptor(mngAuthInterceptor) // 관리자용 로그인
                .addPathPatterns("/mng/**") // path 설정
                .excludePathPatterns("/mng/idx/**")  // 인덱스페이지들은 제외
                .excludePathPatterns(STATIC_RESOURCES);
        
    }
}