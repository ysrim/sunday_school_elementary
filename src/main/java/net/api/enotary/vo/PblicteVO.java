package net.api.enotary.vo;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PblicteVO {

	public PblicteVO() {
	}

	public PblicteVO(String pblicCode, String idntfr, String docHash, String createTime) {
		this.pblicCode = pblicCode;
		this.idntfr = idntfr;
		this.docHash = docHash;
		this.createTime = createTime;
	}

	@NotBlank(message = "pblicCode는 필수 값입니다.")
	private String pblicCode = ""; // 발행 코드

	@NotBlank(message = "idntfr는 필수 값입니다.")
	private String idntfr = ""; // 신청자 식별자

	@NotBlank(message = "docHash는 필수 값입니다.")
	private String docHash = ""; // 문서해쉬

	@NotBlank(message = "createTime는 필수 값입니다.")
	private String createTime = ""; // 작성시간

}
