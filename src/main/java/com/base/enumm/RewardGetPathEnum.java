package com.base.enumm;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RewardGetPathEnum {

	QUEST(1, "퀘스트"),            //
	ADMIN_MANUAL(2, "관리자 수동"), //
	EVENT(3, "이벤트"),            //
	CONTINUITY(4, "연퀘완료"),     //
	LEVEL_UP(5, "레벨업");         //

	private final int seq;

	private final String rewardGetName;

}