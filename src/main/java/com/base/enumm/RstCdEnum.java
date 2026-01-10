package com.base.enumm;

public enum RstCdEnum {

	succ("001") // 성공
	, valid("002") // 변수 부족
	, fail("999") // 실패
	;

	private final String value;

	RstCdEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}