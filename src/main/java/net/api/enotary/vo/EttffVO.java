package net.api.enotary.vo;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EttffVO {

	public EttffVO() {
	}

	public EttffVO(String idntfr, String signHash) {
		this.idntfr = idntfr;
		this.signHash = signHash;
	}

	@NotBlank(message = "idntfr는 필수 값입니다.")
	private String idntfr = ""; // 대표 촉탁인식별자

	@NotBlank(message = "signHash는 필수 값입니다.")
	private String signHash = ""; // 서명해쉬

}