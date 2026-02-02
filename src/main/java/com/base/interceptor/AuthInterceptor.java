package com.base.interceptor;

import java.io.IOException;

import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.base.annotation.MenuInfo;
import com.base.annotation.PassAuth;
import com.base.enumm.NaviEnum;
import com.base.utl.SessionUtil;
import com.base.utl.StringUtil;

import app.idx.lgn.vo.SessionVO;
import app.psn.com.service.CacheService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class AuthInterceptor implements HandlerInterceptor {

	private final CacheService cacheService;

	private static final String LOGIN_PAGE_URL = "/idx/login.pg";

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

		// 1. HandlerMethod 체크
		if (!(handler instanceof HandlerMethod handlerMethod)) {
			return true;
		}

		// 2. 인증 제외 대상 체크
		if (hasPassAuth(handlerMethod)) {
			return true;
		}

		// 3. 세션 인증 체크
		SessionVO sessionVO = SessionUtil.getMberInfo();
		if (sessionVO == null) {
			handleAuthFail(request, response, "Login Required", "401");
			return false;
		}

		// 4. 권한(인가) 체크
		if (!checkMenuAuthorization(request, handlerMethod, sessionVO)) {
			handleAuthFail(request, response, "Access Denied", "403");
			return false;
		}

		// 5. 사용자 부가 정보 설정 (Cache & Request Attributes)
		setupUserContext(request, sessionVO);

		return true;
	}

	private boolean checkMenuAuthorization(HttpServletRequest request, HandlerMethod handlerMethod, SessionVO sessionVO) {
		MenuInfo menuInfo = AnnotatedElementUtils.findMergedAnnotation(handlerMethod.getMethod(), MenuInfo.class);
		if (menuInfo == null)
			return true;

		// 권한 체크
		if (!isAuthorized(sessionVO, menuInfo)) {
			return false;
		}

		// 메뉴 정보 설정
		try {
			String naviKey = menuInfo.navi().toString();
			NaviEnum navi = NaviEnum.valueOf(naviKey);
			request.setAttribute("_menuInfo", naviKey);
			request.setAttribute("_menuNm", navi.getNaviNm());
		} catch (IllegalArgumentException e) {
			log.error("Invalid NaviEnum value in @MenuInfo: {}", menuInfo.navi());
		}

		return true;
	}

	private void setupUserContext(HttpServletRequest request, SessionVO sessionVO) {

		String mberId = sessionVO.getMberId();
		Integer mberSn = sessionVO.getMberSn();

		// 온라인 상태 업데이트
		cacheService.addOnlineMber(mberId);

		// 사용자 포인트/레벨 정보 (한 번의 세션 객체 접근으로 처리)
		request.setAttribute("_mberPoint", StringUtil.comma(cacheService.sltPont(mberSn)));
		request.setAttribute("_mberLevel", cacheService.sltLevel(mberSn));
		request.setAttribute("_mberExp", cacheService.sltExp(mberSn));

	}

	private boolean hasPassAuth(HandlerMethod handler) {
		return AnnotatedElementUtils.hasAnnotation(handler.getMethod(), PassAuth.class) ||
			AnnotatedElementUtils.hasAnnotation(handler.getBeanType(), PassAuth.class);
	}

	private boolean isAuthorized(SessionVO session, MenuInfo menuInfo) {
		// 등급코드가 없는 경우 방어 코드 추가
		if (session.getGradeCode() == null)
			return false;
		return session.getGradeCode().equals(menuInfo.role().getCode());
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