package com.base.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Service
public class LoginInterceptor implements HandlerInterceptor {

	// 1. 컨트롤러 실행 전 (Pre-Handle)
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		log.info(">>> [PreHandle] Request URI: {}", request.getRequestURI());

		// 권한이 있는 페이지만 들어오기 때문에 권한 처리 내용만 들어감.

		// pathauth annotion이 있을 경우 true

		// grade 정보에 맞게 페이지 뷰?

		// 메뉴 annotation 정보가 있으면, 하단 네비게이션 바 설정 셋팅

		// 페이지 url depth 정보 다시 산정해서 url 정보 셋

		// true 반환 시: 다음 인터셉터나 컨트롤러로 진행
		// false 반환 시: 작업 중단 (보통 response에 에러 메시지를 쓰고 끝냄)
		return true;
	}

	// 2. 컨트롤러 실행 후, 뷰 렌더링 전 (Post-Handle)
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		// 코드정보 셋팅 해야함.
		log.info(">>> [PostHandle] 컨트롤러 실행 완료");
	}

	// 3. 뷰 렌더링까지 완료된 후 (After-Completion)
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

		// 페이지 뷰 산정을 위한 데이터 저장 로직 추가

		log.info(">>> [AfterCompletion] 요청 처리 최종 완료");

		if (ex != null) {
			log.error(">>> [Error Occurred] 예외 발생: ", ex);
		}
	}

}
