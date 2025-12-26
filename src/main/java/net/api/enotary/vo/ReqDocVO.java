package net.api.enotary.vo;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReqDocVO {

	@NotBlank(message = "reqstDocIdntfr는 필수 값입니다.")
	private String reqstDocIdntfr = ""; // 촉탁신청 문서 식별자
}