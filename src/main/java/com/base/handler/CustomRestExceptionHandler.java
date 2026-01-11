package com.base.handler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.base.enumm.RstCdEnum;
import com.base.vo.BodyResVO;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class CustomRestExceptionHandler {

	private boolean ajaxType(HttpServletRequest request) {
		return request.getRequestURI().endsWith(".ax") || "XMLHttpRequest".equals(request.getHeader("X-Requested-With")) ? true : false;
	}

	// 아예 잘못된 형식으로 request 를 요청할 경우 예외 발생
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public Object handleHttpMessageNotReadableException(HttpServletRequest request, Model model, HttpMessageNotReadableException ex) {

		// 1. ajax로 호출했을 경우 -> JSON 리턴
		if (ajaxType(request)) {
			BodyResVO error = new BodyResVO();
			error.setRtnCd(RstCdEnum.fail.getValue());
			error.setRtnMsg("Required request body is missing");
			return new ResponseEntity<>(error, HttpStatus.OK);
		}

		// 2. .do (일반 페이지 요청)로 호출했을 경우 -> JSP 리턴
		model.addAttribute("exception", ex.getMessage());
		return "/error/errorPage"; // jsp 경로

	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	public Object handleMissingServletRequestParameterException(HttpServletRequest request, Model model, MissingServletRequestParameterException ex) {

		// 1. ajax로 호출했을 경우 -> JSON 리턴
		if (ajaxType(request)) {
			BodyResVO error = new BodyResVO();
			error.setRtnCd(RstCdEnum.fail.getValue());
			error.setRtnMsg(ex.getParameterName() + " is missing");
			return new ResponseEntity<>(error, HttpStatus.OK);
		}
		// 2. .do (일반 페이지 요청)로 호출했을 경우 -> JSP 리턴
		model.addAttribute("exception", ex.getMessage());

		return "/error/errorPage"; // jsp 경로

	}

	// MethodArgumentNotValidException -> @RequestBody (@Valid JSON) 검증 실패 시
	// BindException -> @ModelAttribute (@Valid Form/QueryString) 검증 실패 시
	@ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
	public Object handleMethodArgumentNotValidException(HttpServletRequest request, Model model, MethodArgumentNotValidException ex) {

		// 1. ajax로 호출했을 경우 -> JSON 리턴
		if (ajaxType(request)) {
			log.debug("ajax validation ex: {}", ex.getMessage());
			BodyResVO body = new BodyResVO();
			body.setRtnCd(RstCdEnum.valid.getValue());
			for (ObjectError error : ex.getBindingResult().getAllErrors()) {
				body.setRtnMsg(error.getDefaultMessage());
				return new ResponseEntity<>(body, HttpStatus.OK);
			}
		}

		// 2. .do (일반 페이지 요청)로 호출했을 경우 -> JSP 리턴
		log.debug("page validation ex: {}", ex.getMessage());
		List<String> params = new ArrayList<>();
		for (FieldError error : ex.getBindingResult().getFieldErrors()) {
			params.add(error.getField());
		}
		String errorStr = String.join(",", params);
		model.addAttribute("exception", errorStr);
		return "/error/errorPage"; // jsp 경로

	}

	@ExceptionHandler({Exception.class, RuntimeException.class})
	public Object handleAllExceptions(HttpServletRequest request, Model model, Exception ex) {

		// 1. ajax로 호출했을 경우 -> JSON 리턴
		if (ajaxType(request)) {
			BodyResVO error = new BodyResVO();
			error.setRtnCd(RstCdEnum.fail.getValue());
			error.setRtnMsg(ex.getMessage());
			return new ResponseEntity<>(error, HttpStatus.OK);
		}
		// 2. .do (일반 페이지 요청)로 호출했을 경우 -> JSP 리턴
		model.addAttribute("exception", ex.getMessage());

		return "/error/errorPage"; // jsp 경로

	}

}