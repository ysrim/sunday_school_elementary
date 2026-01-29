package com.base.enumm;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RewardTypeEnum {

	POINT(1, "달란트"),
	EXP(2, "경험치");

	private final int seq;

	private final String rewardName;

}