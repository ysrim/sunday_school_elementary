package com.base.enumm;

import java.util.Arrays;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum NaviEnum {

	STD_HOME(1, "HOME"),
	STD_HOME_SUB(1, "HOME SUB"),
	STD_ATND(2, "출석체크"),
	STD_GILD(3, "나의 길드"),
	STD_QEST(4, "일일 퀘스트"),
	STD_OPTS(5, "마이 페이지"),
	EMPT(0, "");

	private final int seq;

	private final String naviNm;

}