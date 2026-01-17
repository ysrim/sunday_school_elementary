package com.base.handler;

import java.util.stream.Collectors;

import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.base.enumm.RstCdEnum;
import com.base.utl.ResUtil;
import com.base.utl.SessionUtil;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class CustomRestExceptionHandler {

	private static final String ERROR_VIEW_PATH = "/app/err/errorPage";

	/**
	 * 공통 응답 처리 메소드
	 */
	private Object resolveResponse(HttpServletRequest request, Model model, RstCdEnum rstCd, String message) {
		// 1. Ajax 요청인 경우 JSON 응답 (ResUtil 활용)
		if (SessionUtil.isAjaxRequest(request)) {
			if (rstCd == RstCdEnum.VALID) {
				return ResUtil.resValid(message);
			}
			return ResUtil.resFail(message);
		}

		// 2. 일반 페이지 요청인 경우 View 경로 반환
		model.addAttribute("rtnCd", rstCd.getCode()); // 코드도 같이 넘겨주면 뷰에서 활용 가능
		model.addAttribute("exception", message);
		return ERROR_VIEW_PATH;
	}

	// 1. 잘못된 형식의 Request Body (JSON 파싱 실패)
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public Object handleHttpMessageNotReadableException(HttpServletRequest request, Model model) {
		log.warn("Message Not Readable: {}", request.getRequestURI());
		return resolveResponse(request, model, RstCdEnum.FAIL, "Body formatting error or missing body");
	}

	// 2. 필수 파라미터 누락
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public Object handleMissingServletRequestParameterException(HttpServletRequest request, Model model, MissingServletRequestParameterException ex) {
		log.warn("Missing Parameter: {} [{}]", ex.getParameterName(), request.getRequestURI());
		return resolveResponse(request, model, RstCdEnum.FAIL, String.format("[%s] parameter is missing", ex.getParameterName()));
	}

	// 3. 유효성 검증 실패 (@Valid, @Validated)
	// BindException은 MethodArgumentNotValidException의 부모이므로 하나로 처리가 가능합니다.
	@ExceptionHandler(BindException.class)
	public Object handleBindException(HttpServletRequest request, Model model, BindException ex) {

		// 에러 메시지 추출 로직 분리
		String errorMessage;

		if (SessionUtil.isAjaxRequest(request)) {
			// Ajax: 첫 번째 에러만 간결하게 반환
			errorMessage = ex.getBindingResult().getAllErrors().stream().findFirst().map(e -> e.getDefaultMessage()).orElse(RstCdEnum.VALID.getDefaultMessage());
			log.debug("Validation Fail (Ajax): {}", errorMessage);
		} else {
			// View: 필드명 리스트 반환 (혹은 포맷팅된 메시지)
			errorMessage = ex.getBindingResult().getFieldErrors().stream().map(FieldError::getField).collect(Collectors.joining(", "));
			log.debug("Validation Fail (View): {}", errorMessage);
		}

		return resolveResponse(request, model, RstCdEnum.VALID, errorMessage);
	}

	// 4. 그 외 모든 예외 (최상위 핸들러)
	@ExceptionHandler({Exception.class, RuntimeException.class})
	public Object handleAllExceptions(HttpServletRequest request, Model model, Exception ex) {
		log.error("Unexpected Error [URI: {}]: ", request.getRequestURI(), ex);
		return resolveResponse(request, model, RstCdEnum.FAIL, ex.getMessage());
	}
}