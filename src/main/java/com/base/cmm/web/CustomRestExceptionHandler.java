package com.base.cmm.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.ui.Model;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.base.cmm.enumm.ResultCode;
import com.base.cmm.vo.BodyResVO;

import jakarta.servlet.http.HttpServletRequest;

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
			error.setRtnCd(ResultCode.fail.getValue());
			error.setRtnMsg("Required request body is missing");
			return new ResponseEntity<>(error, HttpStatus.OK);
		}
		// 2. .do (일반 페이지 요청)로 호출했을 경우 -> JSP 리턴
		model.addAttribute("exception", ex.getMessage());

		return "com/error/errorPage"; // jsp 경로

	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	public Object handleMissingServletRequestParameterException(HttpServletRequest request, Model model, MissingServletRequestParameterException ex) {

		// 1. ajax로 호출했을 경우 -> JSON 리턴
		if (ajaxType(request)) {
			BodyResVO error = new BodyResVO();
			error.setRtnCd(ResultCode.fail.getValue());
			error.setRtnMsg(ex.getParameterName() + " is missing");
			return new ResponseEntity<>(error, HttpStatus.OK);
		}
		// 2. .do (일반 페이지 요청)로 호출했을 경우 -> JSP 리턴
		model.addAttribute("exception", ex.getMessage());

		return "com/error/errorPage"; // jsp 경로

	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Object handleMethodArgumentNotValidException(HttpServletRequest request, Model model, MethodArgumentNotValidException ex) {

		List<String> params = new ArrayList<>();
		for (FieldError error : ex.getBindingResult().getFieldErrors()) {
			params.add(error.getField());
		}
		String errorStr = String.join(",", params);

		// 1. ajax로 호출했을 경우 -> JSON 리턴
		if (ajaxType(request)) {
			BodyResVO error = new BodyResVO();
			error.setRtnCd(ResultCode.fail.getValue());
			error.setRtnMsg(errorStr + " is invalid");
			return new ResponseEntity<>(error, HttpStatus.OK);
		}
		// 2. .do (일반 페이지 요청)로 호출했을 경우 -> JSP 리턴
		model.addAttribute("exception", errorStr);

		return "com/error/errorPage"; // jsp 경로

	}

	@ExceptionHandler({Exception.class, RuntimeException.class})
	public Object handleAllExceptions(HttpServletRequest request, Model model, Exception ex) {

		// 1. ajax로 호출했을 경우 -> JSON 리턴
		if (ajaxType(request)) {
			BodyResVO error = new BodyResVO();
			error.setRtnCd(ResultCode.fail.getValue());
			error.setRtnMsg(ex.getMessage());
			return new ResponseEntity<>(error, HttpStatus.OK);
		}
		// 2. .do (일반 페이지 요청)로 호출했을 경우 -> JSP 리턴
		model.addAttribute("exception", ex.getMessage());

		return "com/error/errorPage"; // jsp 경로

	}

}