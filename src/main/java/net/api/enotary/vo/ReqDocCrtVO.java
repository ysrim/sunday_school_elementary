package net.api.enotary.vo;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReqDocCrtVO extends ReqDocVO {

	@NotBlank(message = "idntfr은 필수 값입니다.")
	private String idntfr = ""; // 촉탁신청자 식별자

	@NotBlank(message = "docHash는 필수 값입니다.")
	private String docHash = ""; // 문서 해쉬 정보

	@NotBlank(message = "createTime는 필수 값입니다.")
	private String createTime = ""; // 작성시간, 양식(1970-01-01 00:00:01)

}