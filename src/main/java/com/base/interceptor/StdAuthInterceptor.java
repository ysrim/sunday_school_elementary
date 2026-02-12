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

	@Override
	public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) throws Exception {

		// 1. HandlerMethod(Controller 메서드)가 아니면 통과 (정적 리소스 등)
		// [Java 17] instanceof 패턴 매칭 사용
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
			handleAuthFail(request, response, "Login Required", "401");
			return false;
		}

		// 4. 권한(인가) 체크 및 메뉴 정보 설정
		if (!processMenuAuthorization(request, handlerMethod, stdSessionVO)) {
			handleAuthFail(request, response, "Access Denied", "403");
			return false;
		}

		// 5. 사용자 부가 정보 설정 (Cache & Request Attributes)
		setupUserContext(request, stdSessionVO);

		return true;
	}

	/**
	 * 메뉴 권한을 체크하고, 권한이 있다면 메뉴 정보를 request에 설정합니다.
	 */
	private boolean processMenuAuthorization(HttpServletRequest request, HandlerMethod handlerMethod, StdSessionVO stdSessionVO) {

		// [로직 수정] 우선순위: 메서드 설정(@StdMenuInfo) -> 클래스 설정(@StdMenuInfo)
		// 구체적인 메서드 설정이 우선되어야 합니다.
		StdMenuInfo menuInfo = AnnotatedElementUtils.findMergedAnnotation(handlerMethod.getMethod(), StdMenuInfo.class);

		if (menuInfo == null) {
			menuInfo = AnnotatedElementUtils.findMergedAnnotation(handlerMethod.getBeanType(), StdMenuInfo.class);
		}

		// 메뉴 정보가 없으면 권한 체크 없이 통과 (정책에 따라 false로 변경 가능)
		if (menuInfo == null) {
			return true;
		}

		// 권한 체크 (관리자 등급 체크 등)
		if (!isAuthorized(stdSessionVO)) {
			log.warn("Unauthorized access attempt. User: {}, Grade: {}", stdSessionVO.mberId(), stdSessionVO.gradeCode());
			return false;
		}

		// 메뉴 정보 설정 (View 렌더링용)
		try {
			String naviKey = menuInfo.navi().toString();
			StdNaviEnum navi = StdNaviEnum.valueOf(naviKey);

			request.setAttribute("_stdMenuInfo", naviKey);
			request.setAttribute("_stdMenuNm", navi.getNaviNm());
		} catch (IllegalArgumentException | NullPointerException e) {
			log.error("Invalid StdNaviEnum value in annotation: {}", menuInfo.navi());
			// 치명적이지 않다면 로직 계속 진행
		}

		return true;
	}

	private void setupUserContext(HttpServletRequest request, StdSessionVO stdSessionVO) {
		try {
			String mberId = stdSessionVO.mberId();
			Integer mberSn = stdSessionVO.mberSn();

			// 온라인 상태 업데이트
			cacheService.addOnlineMber(mberId);

			// 사용자 포인트/레벨 정보 설정
			request.setAttribute("_mberPoint", StringUtil.comma(cacheService.sltPont(mberSn)));
			request.setAttribute("_mberLevel", cacheService.sltLevel(mberSn));
			request.setAttribute("_mberExp", cacheService.sltExp(mberSn));
		} catch (Exception e) {
			// 캐시 서버(Redis 등) 장애 시에도 로그인은 유지되도록 예외 처리
			log.warn("Failed to setup user context (Cache Error): {}", e.getMessage());
		}
	}

	private boolean hasPassAuth(HandlerMethod handler) {
		return AnnotatedElementUtils.hasAnnotation(handler.getMethod(), PassAuth.class)
				|| AnnotatedElementUtils.hasAnnotation(handler.getBeanType(), PassAuth.class);
	}

	private boolean isAuthorized(StdSessionVO session) {
		// 관리자 권한(300)은 학생 웹페이지 접속 불가
		return !ADMIN_GRADE_CODE.equals(session.gradeCode());
	}

	private void handleAuthFail(HttpServletRequest request, HttpServletResponse response, String msg, String code) throws IOException {

		if (SessionUtil.isAjaxRequest(request)) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401
			response.setContentType("application/json;charset=UTF-8");

			// Jackson ObjectMapper를 사용하여 안전하게 JSON 생성
			Map<String, String> responseMap = new HashMap<>();
			responseMap.put("rtnCd", code);
			responseMap.put("rtnMsg", msg);

			response.getWriter().write(new ObjectMapper().writeValueAsString(responseMap));
		} else {
			response.sendRedirect(request.getContextPath() + LOGIN_PAGE_URL);
		}
	}
}