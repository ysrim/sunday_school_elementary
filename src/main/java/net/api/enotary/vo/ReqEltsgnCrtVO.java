package net.api.enotary.vo;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReqEltsgnCrtVO extends ReqDocVO {

	@Valid
	@NotNull(message = "ettffReprsnt는 필수 값입니다.")
	private EttffVO ettffReprsnt; // 대표 촉탁인 서명정보

	@Valid
	@NotEmpty(message = "ettffPartcptn는 필수 값입니다.")
	private List<EttffVO> ettffPartcptn; // 참촉탁인 서명정보

	private List<EttffVO> ettffEtc; // 기타 참여인 서명정보

	@Valid
	@NotNull(message = "ntratPsn는 필수 값입니다.")
	private EttffVO ntratPsn; // 공증인 서명정보

}