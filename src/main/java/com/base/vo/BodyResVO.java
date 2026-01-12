package com.base.vo;

import com.base.enumm.RstCdEnum;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor  // Jackson 라이브러리가 JSON 역직렬화 할 때 필요
@AllArgsConstructor // @Builder 사용 시 필요
@JsonInclude(JsonInclude.Include.NON_NULL) // null인 필드는 JSON 응답에서 제외
public class BodyResVO<T> {

	@Builder.Default // Builder 사용 시에도 기본값 적용
	private String rtnCd = RstCdEnum.SUCC.getCode();

	// 알림용 텍스트 (예: "저장되었습니다.", "실패했습니다.")
	private String rtnMsg;

	// 실제 응답 데이터 (예: 조회된 리스트, 사용자 정보 등)
	private T rtnData;

}