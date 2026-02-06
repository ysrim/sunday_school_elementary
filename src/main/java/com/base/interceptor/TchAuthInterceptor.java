package com.base.interceptor;

import java.io.IOException;

import org.jspecify.annotations.NonNull;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.base.annotation.com.PassAuth;
import com.base.annotation.std.StdMenuInfo;
import com.base.enumm.tch.TchNaviEnum;
import com.base.utl.SessionUtil;

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
			handleAuthFail(request, response, "Login Required", "401");
			return false;
		}

		// 4. 권한(인가) 체크
		if (!checkMenuAuthorization(request, handlerMethod, tchSessionVO)) {
			handleAuthFail(request, response, "Access Denied", "403");
			return false;
		}

		return true;

	}

	private boolean checkMenuAuthorization(HttpServletRequest request, HandlerMethod handlerMethod, TchSessionVO tchSessionVO) {

		StdMenuInfo menuInfo = AnnotatedElementUtils.findMergedAnnotation(handlerMethod.getMethod(), StdMenuInfo.class);

		if (menuInfo == null)
			return true;

		// 권한 체크
		if (!isAuthorized(tchSessionVO, menuInfo)) {
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

	private boolean isAuthorized(TchSessionVO session, StdMenuInfo menuInfo) {

		// 선생님 등급코드만 접근 가능하다.
		if (!"200".equals(session.gradeCode()))
			return false;

		return session.gradeCode().equals(menuInfo.role().getCode());

	}

	private void handleAuthFail(HttpServletRequest request, HttpServletResponse response, String msg, String code) throws IOException {

		if (SessionUtil.isAjaxRequest(request)) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().write(String.format("{\"rtnCd\":\"%s\", \"rtnMsg\":\"%s\"}", code, msg));
		} else {
			response.sendRedirect(request.getContextPath() + LOGIN_PAGE_URL);
		}

	}

}