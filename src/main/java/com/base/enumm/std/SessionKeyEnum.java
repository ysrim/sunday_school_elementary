package com.base.enumm.std;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SessionKeyEnum {

    MBER_INFO("mberInfo", "로그인 사용자 정보 (SessionVO)"),
    MENU_INFO("menuInfo", "현재 접속 메뉴 정보 (String/MenuInfo)");

    private final String key;  // HttpSession에 저장될 실제 문자열 Key
    private final String desc; // 무엇이 저장되는지 설명

}