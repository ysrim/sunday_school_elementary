package com.base.enumm.com;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ViewPathEnum {

	DEF("/app"),               // 기본 루트
	STD("/app/psn/std/page"),  // 학생
	TCH("/app/psn/tch/page"),  // 선생
	MNG("/app/psn/mng/page");  // 관리자

	private final String path;

	/**
	 * 경로와 파일명을 결합하여 전체 경로 반환
	 * 예: STD.to("/list") -> "/app/psn/std/page/list"
	 */
	public String to(String fileName) {

		return this.path + fileName;

	}

}