package com.base.enumm;

public enum MberGrdEnum {

	STD("100") // 학생
	, TCH("200") // 선생
	, ADN("300") // 관리자
	;

	private final String value;

	MberGrdEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}