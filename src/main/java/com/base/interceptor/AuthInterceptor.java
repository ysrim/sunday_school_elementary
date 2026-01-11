package com.base.interceptor;

import java.io.IOException;

import app.idx.lgn.vo.SessionVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.base.annotation.MenuInfo;
import com.base.annotation.PassAuth;
import com.base.enumm.SessionKeyEnum;
import com.base.utl.SessionUtil;

@Slf4j
@Component
public class AuthInterceptor implements HandlerInterceptor {

	// 1. 컨트롤러 실행 전 (Pre-Handle)
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

		log.debug(">>> [PreHandle] Request URI: {}", request.getRequestURI());

		// 1. 핸들러 종류 확인 (정적 리소스 요청이 아닌 컨트롤러 메서드인지 확인)
		if (!(handler instanceof HandlerMethod)) {
			return true; // 정적 리소스(css, js 등)라면 바로 통과
		}

		// 2. HandlerMethod로 캐스팅하여 메타정보 접근 권한 획득
		HandlerMethod handlerMethod = (HandlerMethod)handler;

		// 3. clz, method의 annotation 정보가져오기
		MenuInfo menuInfo = handlerMethod.getMethodAnnotation(MenuInfo.class);
		PassAuth pathAuth = handlerMethod.getMethodAnnotation(PassAuth.class);
		if (menuInfo == null || pathAuth != null) { // menuInfo annotation이 없거나, pathAuth annotation이 있으면 true
			log.debug(">>> [Annotation Check] @PathAuth 발견!");
			return true;
		}

		// 4. 로그인한 사용자 session정보를 가져온다.
		SessionVO sessionVO = (SessionVO)SessionUtil.getAttribute(SessionKeyEnum.MBERINFO.name());

		// 5. 로그인한 사용자가 해당 매뉴 접근 가능한지
		if (!menuInfo.role().getValue().equals((sessionVO == null ? "" : sessionVO.getGradeCode()))) {
			log.debug(">>> 권한 불충분으로 페이지 접근 실패! 로그인 페이지로 리다이렉션");
			return loginPage(request, response);
		}

		SessionUtil.setAttribute("menuInfo", menuInfo.name());

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

	private boolean loginPage(HttpServletRequest req, HttpServletResponse res, String rtnParm) {
		try {
			res.sendRedirect(req.getContextPath() + "/idx/login.pg" + rtnParm);
		} catch (IOException e) {
			log.error("loginPage error: {}", e);
		}
		return false;
	}

	private boolean loginPage(HttpServletRequest req, HttpServletResponse res) {
		String rtnPage = req.getRequestURL() + (req.getQueryString() != null ? ("?" + req.getQueryString()) : "");
		String rtnParm = !"".equals(rtnPage) && "GET".equals(req.getMethod()) ? "" : "";
		return this.loginPage(req, res, rtnParm);
	}

}