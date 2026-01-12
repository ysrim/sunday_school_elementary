package com.base.interceptor;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.springframework.core.annotation.AnnotationUtils;
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

	// 1. 컨트롤러 실행 전 (Pre-Handle)
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

		// 1. 정적 리소스 요청은 통과
		if (!(handler instanceof HandlerMethod)) {
			return true;
		}

		HandlerMethod handlerMethod = (HandlerMethod)handler;

		// 2. @PassAuth 체크
		PassAuth passAuth = AnnotationUtils.findAnnotation(handlerMethod.getBeanType(), PassAuth.class);
		// 인증 패스 어노테이션이 있으면 통과
		if (passAuth != null) {
			return true;
		}

		// 3. @MenuInfo 체크 (권한 정보가 없는 경우 정책에 따라 통과 or 차단)
		MenuInfo menuInfo = AnnotationUtils.findAnnotation(handlerMethod.getBeanType(), MenuInfo.class);
		if (menuInfo == null) {
			return true;
		}

		// 4. 로그인 세션 체크
		SessionVO sessionVO = (SessionVO)SessionUtil.getAttribute(SessionKeyEnum.MBER_INFO.name());

		// 4-1. 비로그인 상태 처리
		if (sessionVO == null) {
			log.warn(">>> [Auth Failed] Session is null. Redirect to Login.");
			handleAuthFail(request, response, "Login Required");
			return false;
		}

		// 5. 권한(Role) 체크
		// SessionVO의 등급과 MenuInfo의 요구 등급 비교
		String userGrade = sessionVO.getGradeCode();
		String requiredRole = menuInfo.role().getCode();

		if (!requiredRole.equals(userGrade)) {
			log.warn(">>> [Access Denied] User Grade: {}, Required: {}", userGrade, requiredRole);
			// 403 Forbidden 처리가 맞으나, 편의상 로그인 페이지 혹은 에러 페이지로 보냄
			handleAuthFail(request, response, "Access Denied");
			return false;
		}

		// 6. 메뉴 정보 세션 저장 (화면 표시용)
		SessionUtil.setAttribute("menuInfo", menuInfo.name());

		return true;
	}

	// 2. 컨트롤러 실행 후 (Post-Handle)
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
		log.debug(">>> [PostHandle] View Rendering Start");
	}

	// 3. 요청 완료 후 (After-Completion)
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		if (ex != null) {
			log.error(">>> [Exception] Logic Error: ", ex);
		}
	}

	/**
	 * 인증 실패 처리 (Ajax vs 일반 요청 분기)
	 */
	private void handleAuthFail(HttpServletRequest request, HttpServletResponse response, String msg) throws IOException {

		if (SessionUtil.isAjaxRequest(request)) {
			// Ajax 요청인 경우 401 에러 코드와 JSON 응답 반환
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401
			response.setContentType("application/json;charset=UTF-8");
			// 예: {"rtnCd":"401", "rtnMsg":"Login Required"}
			String jsonResponse = String.format("{\"rtnCd\":\"%s\", \"rtnMsg\":\"%s\"}", "401", msg);
			response.getWriter().write(jsonResponse);
		} else {
			// 일반 요청인 경우 로그인 페이지로 리다이렉트
			response.sendRedirect(request.getContextPath() + LOGIN_PAGE_URL + getRedirectUrl(request));
		}
	}

	/**
	 * 리다이렉트 URL 생성 (현재 페이지 정보를 파라미터로 포함)
	 */
	private String getRedirectUrl(HttpServletRequest request) {
		if ("GET".equalsIgnoreCase(request.getMethod())) {
			String currentUrl = request.getRequestURI();
			String queryString = request.getQueryString();

			if (queryString != null) {
				currentUrl += "?" + queryString;
			}

			// URL 인코딩 처리 (특수문자 등 안전하게 전달)
			try {
				String encodedUrl = URLEncoder.encode(currentUrl, StandardCharsets.UTF_8);
				return "?rtnUrl=" + encodedUrl;
			} catch (Exception e) {
				log.error("URL Encoding Error", e);
			}
		}
		return "";
	}

}