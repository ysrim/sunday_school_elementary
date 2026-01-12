package com.base.enumm;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RstCdEnum {

	// 상수명은 대문자가 관례입니다.
	SUCC("001", "Success"),
	VALID("002", "Validation Failed"),
	FAIL("999", "An error occurred");

	private final String code;
	private final String defaultMessage;

}