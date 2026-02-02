package com.base.enumm;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum NaviEnum {

	STD_HOME(1, "HOME"),
	STD_HOME_1(2, "HOME > 공지사항"),
	STD_ATND(20, "출석체크"),
	STD_GILD(30, "나의 길드"),
	STD_QEST(40, "일일 퀘스트"),
	STD_OPTS(50, "마이 페이지"),
	STD_OPTS_1(51, "마이 페이지 > QR코드"),
	EMPT(0, "");

	private final Integer seq;

	private final String naviNm;

}