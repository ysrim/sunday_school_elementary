package com.base.interceptor;

import app.psn.com.service.CacheService;
import app.psn.std.login.vo.StdSessionVO;
import com.base.annotation.com.PassAuth;
import com.base.annotation.std.StdMenuInfo;
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

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class StdAuthInterceptor implements HandlerInterceptor {

	private final CacheService cacheService;

	private static final String LOGIN_PAGE_URL = "/std/idx/login.pg";

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
			CommonUtil.handleAuthFail(request, response, "Login Required", HttpServletResponse.SC_UNAUTHORIZED, LOGIN_PAGE_URL);
			return false;
		}

		// 4. 권한 체크 및 메뉴 정보 설정
		if (!processMenuAuthorization(request, handlerMethod, stdSessionVO)) {
			CommonUtil.handleAuthFail(request, response, "Access Denied", HttpServletResponse.SC_FORBIDDEN, LOGIN_PAGE_URL);
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

		StdMenuInfo menuInfo = Optional.ofNullable(AnnotatedElementUtils.findMergedAnnotation(handlerMethod.getMethod(), StdMenuInfo.class)) // method
				.orElseGet(() -> AnnotatedElementUtils.findMergedAnnotation(handlerMethod.getBeanType(), StdMenuInfo.class)); // bean

		if (menuInfo == null // // 메뉴 정보가 없으면 실패
				|| !isAuthorized(stdSessionVO) // 권한 체크 (관리자는 학생 페이지 접근 불가 등)
		) {
			return false;
		}

		request.setAttribute("_stdMenuInfo", menuInfo.navi().name());
		request.setAttribute("_stdMenuNm", menuInfo.navi().getNaviNm());
		return true;

	}

	private void setupUserContext(HttpServletRequest request, StdSessionVO stdSessionVO) {

		try {

			// 캐시 서비스 호출 비용이 크다면, 필요할 때만 호출하도록 최적화 고려 필요
			Integer mberSn = stdSessionVO.mberSn();

			cacheService.addOnlineMber(stdSessionVO.mberId());

			request.setAttribute("_mberPoint", CommonUtil.comma(cacheService.sltPont(mberSn)));
			request.setAttribute("_mberLevel", cacheService.sltLevel(mberSn));
			request.setAttribute("_mberExp", cacheService.sltExp(mberSn));

		} catch (Exception e) {

			log.warn("Failed to setup user context (Cache Error): {}", e.getMessage());

		}

	}

	private boolean hasPassAuth(HandlerMethod handler) {

		return AnnotatedElementUtils.hasAnnotation(handler.getMethod(), PassAuth.class) //
				|| AnnotatedElementUtils.hasAnnotation(handler.getBeanType(), PassAuth.class); //

	}

	private boolean isAuthorized(StdSessionVO session) {

		// 학생 페이지는 선생님등급과 학생등급만 접근 가능
		return MberGrdEnum.STD.getCode().equals(session.gradeCode())
				|| MberGrdEnum.TCH.getCode().equals(session.gradeCode());

	}

}