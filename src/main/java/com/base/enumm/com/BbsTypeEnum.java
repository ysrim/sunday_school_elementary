package com.base.enumm.com;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BbsTypeEnum {

	NOTICE("공지"),    //
	EVENT("이벤트"),   //
	GENERAL("일반"),   //
	QUESTION("질문"),  //
	FAQ("자주묻는질문");

	private final String description;

}