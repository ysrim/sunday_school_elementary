package com.base.interceptor;

import java.io.IOException;

import org.jspecify.annotations.NonNull;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import app.psn.com.service.CacheService;
import app.psn.std.login.vo.SessionVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class TeacherAuthInterceptor implements HandlerInterceptor {

	private final CacheService cacheService;

	@Override
	public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) throws IOException {

		return true;
	}

	private boolean checkMenuAuthorization(HttpServletRequest request, HandlerMethod handlerMethod, SessionVO sessionVO) {

		return true;
	}

}