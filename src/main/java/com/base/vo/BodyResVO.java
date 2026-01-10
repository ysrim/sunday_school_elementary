package com.base.vo;

import com.base.enumm.RstCdEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BodyResVO {

	private String rtnCd = RstCdEnum.succ.getValue();

	private Object rtnMsg;

}