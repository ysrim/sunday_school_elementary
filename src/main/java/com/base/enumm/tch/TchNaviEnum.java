package com.base.enumm.tch;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TchNaviEnum {

	TCH_HOME(1, "HOME"),
	TCH_ATND(10, "주일 출석부"),
	TCH_QEST(20, "퀘스트 보드"),
	TCH_GILD(30, "길드"),
	EMPTY(0, "공백");

	private final Integer seq;

	private final String naviNm;

}