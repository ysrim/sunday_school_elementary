package com.base.enumm;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum RewardGetPathEnum {

	QUEST(1, "퀘스트 보상"),            //
	ADMIN_MANUAL(2, "관리자 수동"), //
	EVENT(3, "이벤트 보상"),            //
	CONTINUITY(4, "연퀘 보상"),     //
	LEVEL_UP(5, "레벨업 보상");         //

	private final Integer seq;

	private final String rewardGetName;

	public static RewardGetPathEnum findByName(String name) {
		return Arrays.stream(values()) //
			.filter(e -> e.name().equals(name))
			.findFirst() //
			.orElse(null); // 없으면 null 반환
	}

}