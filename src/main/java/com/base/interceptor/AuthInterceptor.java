package com.base.interceptor;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.base.annotation.MenuInfo;
import com.base.annotation.PassAuth;
import com.base.enumm.SessionKeyEnum;
import com.base.utl.SessionUtil;

import app.idx.lgn.vo.SessionVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthInterceptor implements HandlerInterceptor {

	private static final String LOGIN_PAGE_URL = "/idx/login.pg";
	private static final String RTN_URL_PARAM = "rtnUrl";

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

		// 1. 정적 리소스 등 HandlerMethod가 아닌 경우 통과
		if (!(handler instanceof HandlerMethod)) {
			return true;
		}
		HandlerMethod handlerMethod = (HandlerMethod) handler;

		// 2. @PassAuth 체크 (메서드 우선 -> 클래스 확인)
		if (hasPassAuth(handlerMethod)) {
			return true;
		}

		// 3. 로그인 세션 체크
		SessionVO sessionVO = (SessionVO) SessionUtil.getAttribute(SessionKeyEnum.MBER_INFO.getKey());
		if (sessionVO == null) {
			log.warn(">>> [Auth Failed] Session is null. IP: {}", request.getRemoteAddr());
			handleAuthFail(request, response, "Login Required", "401");
			return false;
		}

		// 4. @MenuInfo 체크 및 권한 검증
		// 메서드 레벨의 @MenuInfo를 먼저 찾고, 없으면 클래스 레벨을 찾음 (필요 시 정책 조정)
		MenuInfo menuInfo = AnnotatedElementUtils.findMergedAnnotation(handlerMethod.getMethod(), MenuInfo.class);

		if (menuInfo != null) {
			// 4-1. 권한 체크
			if (!isAuthorized(sessionVO, menuInfo)) {
				handleAuthFail(request, response, "Access Denied", "403");
				return false;
			}

			// 4-2. 메뉴 정보 저장 (Request Scope 권장)
			// 세션에 저장하면 브라우저 다중 탭 사용 시 정보가 꼬일 수 있음
			request.setAttribute("menuInfo", menuInfo.navi().toString());
		}

		return true;
	}

	/**
	 * PassAuth 어노테이션 존재 여부 확인
	 * (메서드에 붙어있거나, 클래스에 붙어있으면 통과)
	 */
	private boolean hasPassAuth(HandlerMethod handler) {
		return AnnotatedElementUtils.hasAnnotation(handler.getMethod(), PassAuth.class)
				|| AnnotatedElementUtils.hasAnnotation(handler.getBeanType(), PassAuth.class);
	}

	/**
	 * 권한 비교 로직 분리
	 * (단순 문자열 비교 외에 계층형 권한 로직 등을 여기에 구현)
	 */
	private boolean isAuthorized(SessionVO session, MenuInfo menuInfo) {
		String userGrade = session.getGradeCode();
		String requiredRole = menuInfo.role().getCode();
		return requiredRole.equals(userGrade);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
		// request에 담았던 menuInfo를 View로 전달 (필요 시)
		// 만약 jsp/thymeleaf에서 request scope에 직접 접근한다면 생략 가능
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		if (ex != null) {
			log.error(">>> [Exception] Request URI: {}", request.getRequestURI(), ex);
		}
	}

	/**
	 * 인증/인가 실패 처리
	 */
	private void handleAuthFail(HttpServletRequest request, HttpServletResponse response, String msg, String code) throws IOException {
		if (SessionUtil.isAjaxRequest(request)) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.setContentType("application/json;charset=UTF-8");

			// 실제 프로젝트의 응답 DTO를 사용하는 것이 가장 좋음 (예: CommonResponse)
			String jsonResponse = String.format("{\"rtnCd\":\"%s\", \"rtnMsg\":\"%s\"}", code, msg);
			response.getWriter().write(jsonResponse);
		} else {
			response.sendRedirect(request.getContextPath() + LOGIN_PAGE_URL + getRedirectUrl(request));
		}
	}

	/**
	 * 리다이렉트 URL 생성
	 */
	private String getRedirectUrl(HttpServletRequest request) {
		if (!"GET".equalsIgnoreCase(request.getMethod())) {
			return "";
		}

		String currentUrl = request.getRequestURI();
		String queryString = request.getQueryString();

		if (queryString != null) {
			currentUrl += "?" + queryString;
		}

		try {
			return "?" + RTN_URL_PARAM + "=" + URLEncoder.encode(currentUrl, StandardCharsets.UTF_8);
		} catch (Exception e) {
			log.error("URL Encoding Error", e);
			return "";
		}
	}
}