package com.base.utl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.base.enumm.RstCdEnum;
import com.base.vo.BodyResVO;

public class ResUtil {

	public static ResponseEntity resSucc(BodyResVO bodyResVO, Object rtnMsg) {
		bodyResVO.setRtnCd(RstCdEnum.succ.getValue());
		bodyResVO.setRtnMsg(rtnMsg);
		return new ResponseEntity<>(bodyResVO, HttpStatus.OK);
	}

	public static ResponseEntity resFail(BodyResVO bodyResVO, Object rtnMsg) {
		bodyResVO.setRtnCd(RstCdEnum.fail.getValue());
		bodyResVO.setRtnMsg(rtnMsg);
		return new ResponseEntity<>(bodyResVO, HttpStatus.OK);
	}

	public static ResponseEntity resValid(BodyResVO bodyResVO, String validStr) {
		bodyResVO.setRtnCd(RstCdEnum.valid.getValue());
		bodyResVO.setRtnMsg(validStr);
		return new ResponseEntity<>(bodyResVO, HttpStatus.OK);
	}

}