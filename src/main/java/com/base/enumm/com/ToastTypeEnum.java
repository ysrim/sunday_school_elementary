package com.base.enumm.com;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ToastTypeEnum {

	REWARD,  // 리워드
	SUCCESS, // 성공
	WARNING, // 경고
	INFO;     // 정보

}