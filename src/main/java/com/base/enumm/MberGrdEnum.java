package com.base.enumm;

public enum MberGrdEnum {

	STUDENT("100") // 학생
	, TEACHER("200") // 선생
	, MASTER("300") // 마스터
	;

	private final String value;

	MberGrdEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}