package com.base.enumm;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import app.psn.com.vo.TodayBibleVerseVO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CacheKeys {

	OnlineMbers("온라인 맴버"), //
	MberPoint("사용자 달란트 점수"), //
	MberLv("사용자 레벨"), //
	MberExp("사용자 경험치"), //
	TodayBibleVerse("오늘의 말씀"); //

	private final String desc; // 무엇이 저장되는지 설명

	// 아래변수들은 annotaion에 쓰기 위함. 문자열+Enum
	// CacheKeys.MberPoint.name() 또는 .toString() : 메서드 호출은 프로그램이 실행(Runtime)되어야 결과가 나오기 때문에 annotaion에 사용못함
	public static final String OnlineMbersEnum = "OnlineMbers";
	public static final String MberPointEnum = "MberPoint";
	public static final String MberLvEnum = "MberLv";
	public static final String MberExpEnum = "MberExp";
	public static final String TodayBibleVerseEnum = "TodayBibleVerse";

}