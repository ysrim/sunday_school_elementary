package com.base.enumm;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SessionKeyEnum {

	// 명명 규칙: Enum 상수는 대문자 Snake Case 권장 (MBER_INFO)
	// 구조: (실제 세션 Key, 설명 및 저장 타입)
	MBER_INFO("mberInfo", "로그인 사용자 정보 (SessionVO)"),
	MENU_INFO("menuInfo", "현재 접속 메뉴 정보 (String/MenuInfo)");

	private final String key;  // HttpSession에 저장될 실제 문자열 Key
	private final String desc; // 무엇이 저장되는지 설명

}