package com.base.handler;

import com.base.enumm.com.RstCdEnum;
import com.base.utl.ResUtil;
import com.base.utl.SessionUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class CustomRestExceptionHandler {

	private static final String ERROR_VIEW_PATH = "/app/err/errorPage";

	/**
	 * 공통 응답 처리 메소드
	 * - Ajax 요청: ResponseEntity<?> 반환 (JSON)
	 * - 일반 요청: String 반환 (View 경로)
	 */
	private Object resolveResponse(HttpServletRequest request, Model model, RstCdEnum rstCd, String message) {

		// 1. Ajax 요청인 경우: JSON 응답
		if (SessionUtil.isAjaxRequest(request)) {
			return (rstCd == RstCdEnum.VALID) ? ResUtil.resValid(message) : ResUtil.resFail(message);
		}

		// 2. 일반 페이지 요청인 경우: View 경로 반환
		model.addAttribute("rtnCd", rstCd.getCode());
		model.addAttribute("rtnMsg", message);

		return ERROR_VIEW_PATH;
	}

	/**
	 * 잘못된 형식의 Request Body (JSON 파싱 실패)
	 */
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public Object handleHttpMessageNotReadableException(HttpServletRequest request, Model model) {

		log.warn("[Bad Request] Message Not Readable: {}", request.getRequestURI());

		return resolveResponse(request, model, RstCdEnum.ERR, "잘못된 요청 형식입니다.");

	}

	/**
	 * 필수 파라미터 누락
	 */
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public Object handleMissingServletRequestParameterException(HttpServletRequest request, Model model, MissingServletRequestParameterException ex) {

		log.warn("[Missing Parameter] {} : {}", ex.getParameterName(), request.getRequestURI());

		return resolveResponse(request, model, RstCdEnum.ERR, String.format("필수 파라미터[%s]가 누락되었습니다.", ex.getParameterName()));

	}

	/**
	 * 유효성 검증 실패 (@Valid, @Validated)
	 */
	@ExceptionHandler(BindException.class)
	public Object handleBindException(HttpServletRequest request, Model model, BindException ex) {

		String errorMessage;

		if (SessionUtil.isAjaxRequest(request)) {
			// Ajax: 가장 첫 번째 에러 메시지 하나만 명확하게 전달
			FieldError firstError = ex.getBindingResult().getFieldError();
			errorMessage = (firstError != null) ? firstError.getDefaultMessage() : "입력값이 올바르지 않습니다.";
		} else {
			// View: 어떤 필드들이 문제인지 나열 (사용자 경험에 따라 수정 가능)
			errorMessage = ex.getBindingResult().getFieldErrors().stream()
					.map(error -> String.format("%s: %s", error.getField(), error.getDefaultMessage()))
					.collect(Collectors.joining(", "));
		}

		log.info("[Validation Fail] URI: {}, Msg: {}", request.getRequestURI(), errorMessage);

		// 유효성 검사는 시스템 에러가 아니므로 VALID(또는 별도 경고 코드) 사용
		return resolveResponse(request, model, RstCdEnum.VALID, errorMessage);

	}

	/**
	 * 그 외 모든 예외 (최상위 핸들러)
	 */
	@ExceptionHandler(Exception.class)
	public Object handleAllExceptions(HttpServletRequest request, Model model, Exception ex) {

		// 실제 에러 로그는 서버에만 남김 (스택 트레이스 포함)
		log.error("[Unexpected Error] URI: {}", request.getRequestURI(), ex);

		// 보안: 사용자에게는 내부 예외 메시지(ex.getMessage)를 그대로 노출하지 않고 일반적인 메시지 제공
		// ex.getMessage()를 그대로 보내면 SQL 구조나 내부 로직이 노출될 위험이 있음
		return resolveResponse(request, model, RstCdEnum.ERR, "시스템 오류가 발생했습니다. 관리자에게 문의해주세요.");

	}
}