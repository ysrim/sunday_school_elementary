package com.base.interceptor;

import org.jspecify.annotations.NonNull;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.base.annotation.com.PassAuth;
import com.base.annotation.mng.MngMenuInfo;
import com.base.enumm.mng.MngNaviEnum;
import com.base.utl.SessionUtil;
import com.base.utl.StringUtil;

import app.psn.mng.login.vo.MngSessionVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class MngAuthInterceptor implements HandlerInterceptor {

	// [상수화] 유지보수를 위해 주요 값들을 상수로 분리
	private static final String LOGIN_PAGE_URL = "/mng/idx/login.pg";
	private static final String ADMIN_GRADE_CODE = "300";
	private static final String ATTR_MENU_KEY = "_mngMenuInfo";
	private static final String ATTR_MENU_NM = "_mngMenuNm";

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
			StringUtil.handleAuthFail(request, response, "Login Required", HttpServletResponse.SC_UNAUTHORIZED, LOGIN_PAGE_URL);
			return false;
		}

		// 4. 권한(인가) 및 메뉴 정보 설정
		if (!checkMenuAuthorization(request, handlerMethod, mngSessionVO)) {
			StringUtil.handleAuthFail(request, response, "Access Denied", HttpServletResponse.SC_FORBIDDEN, LOGIN_PAGE_URL);
			return false;
		}

		return true;

	}

	/**
	 * 메뉴 권한 체크 및 요청 속성 설정
	 */
	private boolean checkMenuAuthorization(HttpServletRequest request, HandlerMethod handlerMethod, MngSessionVO mngSessionVO) {

		// 관리자 등급 체크 (공통)
		if (!isAuthorized(mngSessionVO)) {
			return false;
		}

		MngMenuInfo menuInfo = AnnotatedElementUtils.findMergedAnnotation(handlerMethod.getMethod(), MngMenuInfo.class);

		// 메뉴 정보가 없으면 통과 (단순 로직 수행)
		if (menuInfo == null) {
			return true;
		}

		try {
			// [최적화] Enum 자체를 바로 활용 (toString -> valueOf 불필요)
			MngNaviEnum navi = menuInfo.navi();

			request.setAttribute(ATTR_MENU_KEY, navi.name());
			request.setAttribute(ATTR_MENU_NM, navi.getNaviNm());

			return true;
		} catch (Exception e) {
			// [보안] 메뉴 정보 파싱 중 에러 발생 시 접근 차단이 안전함
			log.error("Failed to parse MenuInfo: {}", e.getMessage());
			return false;
		}

	}

	private boolean hasPassAuth(HandlerMethod handler) {

		return AnnotatedElementUtils.hasAnnotation(handler.getMethod(), PassAuth.class)
			|| AnnotatedElementUtils.hasAnnotation(handler.getBeanType(), PassAuth.class);

	}

	private boolean isAuthorized(MngSessionVO session) {

		return ADMIN_GRADE_CODE.equals(session.gradeCode());

	}

}