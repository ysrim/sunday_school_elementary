package com.base.vo;

import com.base.enumm.com.RstCdEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ResponseBody<T>(String rtnCd, String rtnMsg, T rtnData) {

	public ResponseBody {

		rtnCd = rtnCd == null ? RstCdEnum.SUCC.getCode() : rtnCd;

	}

	// 2. 편의를 위한 정적 팩토리 메서드 (선택 사항)
	public static <T> ResponseBody<T> success(T data, String rtnMsg) {

		return new ResponseBody<>(RstCdEnum.SUCC.getCode(), rtnMsg, data);

	}

	public static <T> ResponseBody<T> error(String msg) {

		return new ResponseBody<>(RstCdEnum.ERR.getCode(), msg, null);

	}

}