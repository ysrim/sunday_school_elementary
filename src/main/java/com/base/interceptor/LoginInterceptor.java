package com.base.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.base.annotation.PassAuth;

@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {

	// 1. 컨트롤러 실행 전 (Pre-Handle)
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		log.debug(">>> [PreHandle] Request URI: {}", request.getRequestURI());

		// 1. 핸들러 종류 확인 (정적 리소스 요청이 아닌 컨트롤러 메서드인지 확인)
		if (!(handler instanceof HandlerMethod)) {
			return true; // 정적 리소스(css, js 등)라면 바로 통과
		}

		// 2. HandlerMethod로 캐스팅하여 메타정보 접근 권한 획득
		HandlerMethod handlerMethod = (HandlerMethod) handler;

		// 3. 메서드(Method) 레벨 어노테이션 가져오기 @PathAuth
		PassAuth pathAuth = handlerMethod.getMethodAnnotation(PassAuth.class);
		if (pathAuth != null) {
			log.debug(">>> [Annotation Check] @PathAuth 발견!");
			return true;
		}


		// 여기에 권한 체크 로직 구현
		// if (!user.hasRole(pathAuth.role())) return false;
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
