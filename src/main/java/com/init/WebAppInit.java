package com.init;

import java.util.EnumSet;

import com.config.*;

import jakarta.servlet.Filter;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.SessionTrackingMode;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebAppInit extends AbstractAnnotationConfigDispatcherServletInitializer {

    // Root Context (Service, Repository, DB 등)
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{AppConfig.class}; // properties, db conn, password encode, chache, scheduler
    }

    // Servlet Context (Controller, ViewResolver, Interceptor 등)
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{WebConfig.class};
    }

    // DispatcherServlet 매핑 패턴
    @Override
    protected String[] getServletMappings() {
        return new String[]{"*.do", "*.ax", "*.pg"};
    }

    // 인코딩 필터 등록 (web.xml의 filter 설정 대체)
    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding("UTF-8");
        encodingFilter.setForceEncoding(true);
        return new Filter[]{encodingFilter};
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        // 반드시 super.onStartup()을 먼저 호출해야 DispatcherServlet이 정상 등록됩니다.
        super.onStartup(servletContext);
        // 1. 세션 타임아웃 설정 (분 단위) -> 30분
        servletContext.setSessionTimeout(30);
        // 2. 쿠키 추적 모드 설정 (URL에 jsessionid 붙는 것 방지)
        servletContext.setSessionTrackingModes(EnumSet.of(SessionTrackingMode.COOKIE));
    }

}