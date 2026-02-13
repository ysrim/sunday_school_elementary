package com.base.interceptor;

import app.psn.mng.login.vo.MngSessionVO;
import com.base.annotation.com.PassAuth;
import com.base.annotation.mng.MngMenuInfo;
import com.base.enumm.com.MberGrdEnum;
import com.base.utl.SessionUtil;
import com.base.utl.CommonUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class MngAuthInterceptor implements HandlerInterceptor {

	private static final String LOGIN_PAGE_URL = "/mng/idx/login.pg";

	@Override
	public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) throws Exception {

		// 1. HandlerMethod 체크 (정적 리소스 제외)
		if (!(handler instanceof HandlerMethod handlerMethod)) {
			return true;
		}

		// 2. 인증 제외 대상(@PassAuth) 체크
		if (hasPassAuth(handlerMethod)) {
			return true;
		}

		// 3. 세션 인증 체크
		MngSessionVO mngSessionVO = SessionUtil.getMngMberInfo();
		if (mngSessionVO == null) {
			CommonUtil.handleAuthFail(request, response, "Login Required", HttpServletResponse.SC_UNAUTHORIZED, LOGIN_PAGE_URL);
			return false;
		}

		// 4. 권한 및 메뉴 정보 설정
		if (!checkMenuAuthorization(request, handlerMethod, mngSessionVO)) {
			CommonUtil.handleAuthFail(request, response, "Access Denied", HttpServletResponse.SC_FORBIDDEN, LOGIN_PAGE_URL);
			return false;
		}

		return true;

	}

	/**
	 * 메뉴 권한 체크 및 요청 속성 설정
	 */
	private boolean checkMenuAuthorization(HttpServletRequest request, HandlerMethod handlerMethod, MngSessionVO mngSessionVO) {

		MngMenuInfo menuInfo = Optional.ofNullable(AnnotatedElementUtils.findMergedAnnotation(handlerMethod.getMethod(), MngMenuInfo.class)) // method
				.orElseGet(() -> AnnotatedElementUtils.findMergedAnnotation(handlerMethod.getBeanType(), MngMenuInfo.class)); // bean

		if (menuInfo == null // 메뉴 정보가 없으면 실패
				|| !isAuthorized(mngSessionVO) //  관리자 등급 체크 (공통)
		) {
			return false;
		}

		request.setAttribute("_mngMenuInfo", menuInfo.navi().name());
		request.setAttribute("_mngMenuNm", menuInfo.navi().getNaviNm());
		return true;

	}

	private boolean hasPassAuth(HandlerMethod handler) {

		return AnnotatedElementUtils.hasAnnotation(handler.getMethod(), PassAuth.class) // method
				|| AnnotatedElementUtils.hasAnnotation(handler.getBeanType(), PassAuth.class); // bean

	}

	private boolean isAuthorized(MngSessionVO session) {

		return MberGrdEnum.MNG.is(session.gradeCode());

	}

}