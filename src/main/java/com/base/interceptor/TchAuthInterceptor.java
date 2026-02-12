package com.base.interceptor;

import java.io.IOException;

import com.base.annotation.tch.TchMenuInfo;

import org.jspecify.annotations.NonNull;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.base.annotation.com.PassAuth;
import com.base.enumm.tch.TchNaviEnum;
import com.base.utl.SessionUtil;
import com.base.utl.StringUtil;

import app.psn.tch.login.vo.TchSessionVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class TchAuthInterceptor implements HandlerInterceptor {

	private static final String LOGIN_PAGE_URL = "/tch/idx/login.pg";

	@Override
	public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) throws IOException {

		// 1. HandlerMethod 체크
		if (!(handler instanceof HandlerMethod handlerMethod)) {
			return true;
		}

		// 2. 인증 제외 대상 체크
		if (hasPassAuth(handlerMethod)) {
			return true;
		}

		// 3. 세션 인증 체크
		TchSessionVO tchSessionVO = SessionUtil.getTchMberInfo();
		if (tchSessionVO == null) {
			StringUtil.handleAuthFail(request, response, "Login Required", HttpServletResponse.SC_UNAUTHORIZED, LOGIN_PAGE_URL);
			return false;
		}

		// 4. 권한(인가) 체크
		if (!checkMenuAuthorization(request, handlerMethod, tchSessionVO)) {
			StringUtil.handleAuthFail(request, response, "Access Denied", HttpServletResponse.SC_FORBIDDEN, LOGIN_PAGE_URL);
			return false;
		}

		return true;

	}

	private boolean checkMenuAuthorization(HttpServletRequest request, HandlerMethod handlerMethod, TchSessionVO tchSessionVO) {

		TchMenuInfo menuInfo = AnnotatedElementUtils.findMergedAnnotation(handlerMethod.getMethod(), TchMenuInfo.class);

		if (menuInfo == null) {
			menuInfo = AnnotatedElementUtils.findMergedAnnotation(handlerMethod.getBeanType(), TchMenuInfo.class);
		}

		if (menuInfo == null)
			return true;

		// 권한 체크
		if (!isAuthorized(tchSessionVO)) {
			return false;
		}

		// 메뉴 정보 설정
		try {
			String naviKey = menuInfo.navi().toString();
			TchNaviEnum navi = TchNaviEnum.valueOf(naviKey);
			request.setAttribute("_tchMenuInfo", naviKey);
			request.setAttribute("_tchMenuNm", navi.getNaviNm());
		} catch (IllegalArgumentException e) {
			log.error("Invalid NaviEnum value in @MenuInfo: {}", menuInfo.navi());
		}

		return true;

	}

	private boolean hasPassAuth(HandlerMethod handler) {

		return AnnotatedElementUtils.hasAnnotation(handler.getMethod(), PassAuth.class) || AnnotatedElementUtils.hasAnnotation(handler.getBeanType(), PassAuth.class);

	}

	private boolean isAuthorized(TchSessionVO session) {

		return "200".equals(session.gradeCode());

	}

}