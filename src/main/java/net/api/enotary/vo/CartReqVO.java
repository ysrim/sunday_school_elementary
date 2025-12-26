package net.api.enotary.vo;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

public class CartReqVO {

	@Getter
	@Setter
	private String accessToken = "";

	@Getter
	@Setter
	@NotBlank(message = "goodsNo는 필수 값입니다.")
	private String goodsNo = "";

}