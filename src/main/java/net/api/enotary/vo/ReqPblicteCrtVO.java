package net.api.enotary.vo;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReqPblicteCrtVO extends ReqDocVO {

	@Valid
	@NotNull(message = "pblicteHist는 필수 값입니다.")
	private PblicteVO pblicteHist; // 발행이력정보

}