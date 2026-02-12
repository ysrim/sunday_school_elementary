package com.base.interceptor;

import app.psn.com.service.CacheService;
import app.psn.std.login.vo.StdSessionVO;

import com.base.annotation.com.PassAuth;
import com.base.annotation.std.StdMenuInfo;
import com.base.enumm.std.StdNaviEnum;
import com.base.utl.SessionUtil;
import com.base.utl.StringUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.lang.NonNull;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
public class StdAuthInterceptor implements HandlerInterceptor {

	private final CacheService cacheService;

	private static final String LOGIN_PAGE_URL = "/std/idx/login.pg";
	private static final String ADMIN_GRADE_CODE = "300";

	private static final String ATTR_MENU_KEY = "_stdMenuInfo";
	private static final String ATTR_MENU_NM = "_stdMenuNm";
	private static final String ATTR_MBER_POINT = "_mberPoint";
	private static final String ATTR_MBER_LEVEL = "_mberLevel";
	private static final String ATTR_MBER_EXP = "_mberExp";

	@Override
	public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) throws Exception {

		// 1. HandlerMethod 체크
		if (!(handler instanceof HandlerMethod handlerMethod)) {
			return true;
		}

		// 2. 인증 제외 대상(@PassAuth) 체크
		if (hasPassAuth(handlerMethod)) {
			return true;
		}

		// 3. 세션 인증 체크
		StdSessionVO stdSessionVO = SessionUtil.getStdMberInfo();
		if (stdSessionVO == null) {
			StringUtil.handleAuthFail(request, response, "Login Required", HttpServletResponse.SC_UNAUTHORIZED, LOGIN_PAGE_URL);
			return false;
		}

		// 4. 권한(인가) 체크 및 메뉴 정보 설정
		if (!processMenuAuthorization(request, handlerMethod, stdSessionVO)) {
			StringUtil.handleAuthFail(request, response, "Access Denied", HttpServletResponse.SC_FORBIDDEN, LOGIN_PAGE_URL);
			return false;
		}

		// 5. 사용자 부가 정보 설정 (Cache & Request Attributes)
		setupUserContext(request, stdSessionVO);

		return true;
	}

	/**
	 * 메뉴 권한 체크 및 정보 설정
	 */
	private boolean processMenuAuthorization(HttpServletRequest request, HandlerMethod handlerMethod, StdSessionVO stdSessionVO) {

		StdMenuInfo menuInfo = AnnotatedElementUtils.findMergedAnnotation(handlerMethod.getMethod(), StdMenuInfo.class);
		if (menuInfo == null) {
			menuInfo = AnnotatedElementUtils.findMergedAnnotation(handlerMethod.getBeanType(), StdMenuInfo.class);
		}

		// 메뉴 정보가 없으면 통과
		if (menuInfo == null) {
			return true;
		}

		// 권한 체크 (관리자는 학생 페이지 접근 불가 등)
		if (!isAuthorized(stdSessionVO)) {
			log.warn("Unauthorized access attempt. User: {}, Grade: {}", stdSessionVO.mberId(), stdSessionVO.gradeCode());
			return false;
		}

		try {
			StdNaviEnum navi = menuInfo.navi(); // Enum 자체를 바로 사용
			request.setAttribute(ATTR_MENU_KEY, navi.name());
			request.setAttribute(ATTR_MENU_NM, navi.getNaviNm());
		} catch (Exception e) {
			log.error("Failed to set menu info: {}", e.getMessage());
		}

		return true;
	}

	private void setupUserContext(HttpServletRequest request, StdSessionVO stdSessionVO) {
		try {
			// 캐시 서비스 호출 비용이 크다면, 필요할 때만 호출하도록 최적화 고려 필요
			Integer mberSn = stdSessionVO.mberSn();

			cacheService.addOnlineMber(stdSessionVO.mberId());

			request.setAttribute(ATTR_MBER_POINT, StringUtil.comma(cacheService.sltPont(mberSn)));
			request.setAttribute(ATTR_MBER_LEVEL, cacheService.sltLevel(mberSn));
			request.setAttribute(ATTR_MBER_EXP, cacheService.sltExp(mberSn));
		} catch (Exception e) {
			log.warn("Failed to setup user context (Cache Error): {}", e.getMessage());
		}
	}

	private boolean hasPassAuth(HandlerMethod handler) {
		return AnnotatedElementUtils.hasAnnotation(handler.getMethod(), PassAuth.class)
			|| AnnotatedElementUtils.hasAnnotation(handler.getBeanType(), PassAuth.class);
	}

	private boolean isAuthorized(StdSessionVO session) {
		// 관리자(300) 등급은 학생 페이지 접근 불가 로직으로 보임
		return !ADMIN_GRADE_CODE.equals(session.gradeCode());
	}

}