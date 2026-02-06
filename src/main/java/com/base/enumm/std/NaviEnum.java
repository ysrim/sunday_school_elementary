package com.base.enumm.std;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum NaviEnum {

    STD_HOME(1, "HOME"),
    STD_HOME_1(2, "공지사항"),
    STD_ATND(20, "주일 출석부"),
    STD_GILD(30, "길드 본부"),
    STD_QEST(40, "퀘스트 보드"),
    STD_OPTS(50, "마이 페이지"),
    STD_OPTS_1(51, "나의 QR코드"),
    STD_OPTS_2(52, "비밀번호 변경"),
    STD_OPTS_3(53, "포인트/경험치 내역"),
    EMPTY(0, "공백");

    private final Integer seq;

    private final String naviNm;

}