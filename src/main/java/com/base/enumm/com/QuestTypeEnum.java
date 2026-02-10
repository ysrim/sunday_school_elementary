package com.base.enumm.com;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum QuestTypeEnum {

	DAILY("일일퀘스트"),  //
	WEEKLY("주일퀘스트"), //
	EVENT("이벤트"),     //
	ONE_TIME("절기");    //

	private final String description;

	public boolean isSameStatus(String nm) {
		return this.name().equals(nm);
	}

}