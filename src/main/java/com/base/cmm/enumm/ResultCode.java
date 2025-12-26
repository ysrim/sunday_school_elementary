package com.base.cmm.enumm;

public enum ResultCode {

	succ("001") // 성공
	, valid("002") // 변수 부족
	, fail("999") // 실패
	;

	private final String value;

	ResultCode(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}