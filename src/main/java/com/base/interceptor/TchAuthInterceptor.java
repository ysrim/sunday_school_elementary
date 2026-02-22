package com.base.interceptor;

import app.psn.tch.login.vo.TchSessionVO;

import com.base.annotation.com.PassAuth;
import com.base.annotation.tch.TchMenuInfo;
import com.base.enumm.com.MberGrdEnum;
import com.base.utl.SessionUtil;
import com.base.utl.CommonUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.lang.NonNull;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;
import java.util.Optional;

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
			CommonUtil.handleAuthFail(request, response, "Login Required", HttpServletResponse.SC_UNAUTHORIZED, LOGIN_PAGE_URL);
			return false;
		}

		// 4. 권한(인가) 체크
		if (!checkMenuAuthorization(request, handlerMethod, tchSessionVO)) {
			CommonUtil.handleAuthFail(request, response, "Access Denied", HttpServletResponse.SC_FORBIDDEN, LOGIN_PAGE_URL);
			return false;
		}

		return true;

	}

	/**
	 * 메뉴권한 체크 및 메뉴 정보 설정
	 */
	private boolean checkMenuAuthorization(HttpServletRequest request, HandlerMethod handlerMethod, TchSessionVO tchSessionVO) {

		TchMenuInfo menuInfo = Optional.ofNullable(AnnotatedElementUtils.findMergedAnnotation(handlerMethod.getMethod(), TchMenuInfo.class)) // method
			.orElseGet(() -> AnnotatedElementUtils.findMergedAnnotation(handlerMethod.getBeanType(), TchMenuInfo.class)); // bean

		if (menuInfo == null || !isAuthorized(tchSessionVO))
			return false;

		request.setAttribute("_tchMenuInfo", menuInfo.navi().name());
		request.setAttribute("_tchMenuNm", menuInfo.navi().getNaviNm());

		return true;

	}

	private boolean hasPassAuth(HandlerMethod handler) {

		return AnnotatedElementUtils.hasAnnotation(handler.getMethod(), PassAuth.class) //
			|| AnnotatedElementUtils.hasAnnotation(handler.getBeanType(), PassAuth.class); //

	}

	private boolean isAuthorized(TchSessionVO session) {

		// 선생님 권한만 접근 가능
		return MberGrdEnum.TCH.is(session.gradeCode());

	}

}