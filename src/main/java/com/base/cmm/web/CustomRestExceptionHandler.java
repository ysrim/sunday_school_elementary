package com.base.cmm.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.base.cmm.enumm.ResultCode;
import com.base.cmm.vo.BodyResVO;

@ControllerAdvice
public class CustomRestExceptionHandler {

	@ExceptionHandler(HttpMessageNotReadableException.class)
	@ResponseBody
	public BodyResVO handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {

		// 아예 잘못된 형식으로 request 를 요청할 경우 예외 발생
		BodyResVO error = new BodyResVO();
		error.setRtnCd(ResultCode.fail.getValue());
		error.setRtnMsg("Required request body is missing");

		return error;

	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	@ResponseBody
	public BodyResVO handleMissingServletRequestParameterException(MissingServletRequestParameterException ex) {

		BodyResVO error = new BodyResVO();
		error.setRtnCd(ResultCode.fail.getValue());
		error.setRtnMsg(ex.getParameterName() + " is missing");

		return error;

	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseBody
	public BodyResVO handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {

		List<String> params = new ArrayList<>();
		for (FieldError error : ex.getBindingResult().getFieldErrors()) {
			params.add(error.getField());
		}
		String errorStr = String.join(",", params);

		BodyResVO error = new BodyResVO();
		error.setRtnCd(ResultCode.fail.getValue());
		error.setRtnMsg(errorStr + " is invalid");

		return error;

	}

	@ExceptionHandler(Exception.class)
	@ResponseBody
	public final BodyResVO handleAllExceptions(Exception ex) {

		BodyResVO error = new BodyResVO();
		error.setRtnCd(ResultCode.fail.getValue());
		error.setRtnMsg(ex.getMessage());

		return error;

	}

}