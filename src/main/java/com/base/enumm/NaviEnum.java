package com.base.enumm;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum NaviEnum {

	STD_HOME(1),
	STD_ATND(2),
	STD_GILD(3),
	STD_QEST(4),
	STD_OPTS(5),
	EMPT(0);

	private final int seq;

}