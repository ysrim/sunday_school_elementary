package com.base.enumm;

import java.util.Arrays;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MberGrdEnum {

	// 1. 명확한 영문명 사용 권장 (STD -> STUDENT)
	// 2. 한글 설명(desc) 필드 추가
	STD("100", "학생"),
	TCH("200", "선생님"),
	ADN("300", "관리자");

	private final String code; // DB에 저장되는 값
	private final String desc; // 화면에 보여줄 값

	/**
	 * 코드값으로 Enum 찾기 (DB 데이터 -> Enum 변환 시 사용)
	 * 예: MberGrdEnum.of("100") -> MberGrdEnum.STUDENT
	 */
	public static MberGrdEnum of(String code) {
		return Arrays.stream(values())
			.filter(r -> r.code.equals(code))
			.findAny()
			.orElse(null); // 혹은 예외 발생
	}

	/**
	 * 안전한 비교를 위한 메서드 (Null Safe)
	 */
	public boolean is(String code) {
		return this.code.equals(code);
	}
}