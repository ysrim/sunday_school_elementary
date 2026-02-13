package com.base.enumm.com;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MberGrdEnum {

	STD("100", "학생"),   //
	TCH("200", "선생님"), //
	MNG("300", "관리자");  //

	private final String code; // DB에 저장되는 값
	private final String desc; // 화면에 보여줄 값

	/**
	 * 안전한 비교를 위한 메서드 (Null Safe)
	 */
	public boolean is(String code) {
		return this.code.equals(code);
	}

}