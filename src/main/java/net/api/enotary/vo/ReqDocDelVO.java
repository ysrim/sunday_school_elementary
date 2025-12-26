package net.api.enotary.vo;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReqDocDelVO extends ReqDocVO {

	@NotBlank(message = "useYn는 필수 값입니다.")
	private String useYn = ""; // 문서 사용 여부

}