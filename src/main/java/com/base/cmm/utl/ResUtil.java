package com.base.cmm.utl;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import com.base.cmm.enumm.ResultCode;
import com.base.cmm.vo.BodyResVO;

public class ResUtil {

	public static BodyResVO resValid(BodyResVO bodyResVO, BindingResult bindingResult) {
		bodyResVO.setRtnCd(ResultCode.valid.getValue());
		for (ObjectError error : bindingResult.getAllErrors()) {
			bodyResVO.setRtnMsg(error.getDefaultMessage());
			return bodyResVO;
		}
		return bodyResVO;
	}

	public static BodyResVO resSucc(BodyResVO bodyResVO, Object rtnMsg) {
		bodyResVO.setRtnCd(ResultCode.succ.getValue());
		bodyResVO.setRtnMsg(rtnMsg);
		return bodyResVO;
	}

	public static BodyResVO resFail(BodyResVO bodyResVO, Object rtnMsg) {
		bodyResVO.setRtnCd(ResultCode.fail.getValue());
		bodyResVO.setRtnMsg(rtnMsg);
		return bodyResVO;
	}

	public static BodyResVO resValid(BodyResVO bodyResVO, String validStr) {
		bodyResVO.setRtnCd(ResultCode.valid.getValue());
		bodyResVO.setRtnMsg(validStr);
		return bodyResVO;
	}

}