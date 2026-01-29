package com.base.enumm;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RewardGetPathEnum {

	QUEST(1, "퀘스트 습득"),
	MANUAL(2, "수동 습득");

	private final int seq;

	private final String rewardGetName;

}