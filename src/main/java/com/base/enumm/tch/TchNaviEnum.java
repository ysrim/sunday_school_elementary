package com.base.enumm.tch;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TchNaviEnum {

	TCD_HOME(1, "HOME"),
	TCD_HOME_1(2, "공지사항"),
	TCD_ATND(20, "주일 출석부"),
	TCD_GILD(30, "길드 본부"),
	TCD_QEST(40, "퀘스트 보드"),
	TCD_OPTS(50, "마이 페이지"),
	TCD_OPTS_1(51, "나의 QR코드"),
	TCD_OPTS_2(52, "비밀번호 변경"),
	TCD_OPTS_3(53, "포인트/경험치 내역"),
	EMPTY(0, "공백");

	private final Integer seq;

	private final String naviNm;

}