package com.base.enumm.mng;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MngNaviEnum {

	MNG_HOME(1, "대쉬보드"),
	MNG_MBER(10, "회원 관리"),
	MNG_FEED(20, "게시판 관리"),
	MNG_REWD(30, "리워드 관리"),
	MNG_QEST(40, "퀘스트 승인 관리"),
	EMPTY(0, "공백");

	private final Integer seq;

	private final String naviNm;

}