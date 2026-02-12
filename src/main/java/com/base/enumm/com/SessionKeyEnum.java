package com.base.enumm.com;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SessionKeyEnum {

	STD_MBER_INFO("stdMberInfo", "학생 사용자 정보 (StdSessionVO)"),         //
	STD_MENU_INFO("stdMenuInfo", "학생 접속 메뉴 정보 (String/MenuInfo)"),   //
	TCH_MBER_INFO("tchMberInfo", "선생님 사용자 정보 (SessionVO)"),          //
	TCH_MENU_INFO("tchMenuInfo", "선생님 접속 메뉴 정보 (String/MenuInfo)"),  //
	MNG_MBER_INFO("mngMberInfo", "관리자 사용자 정보 (SessionVO)"),           //
	MNG_MENU_INFO("mngMenuInfo", "관리자 접속 메뉴 정보 (String/MenuInfo)");  //

	private final String key;  // HttpSession에 저장될 실제 문자열 Key
	private final String desc; // 무엇이 저장되는지 설명

}