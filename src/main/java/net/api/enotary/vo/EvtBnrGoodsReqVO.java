package net.api.enotary.vo;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

public class EvtBnrGoodsReqVO {

	@Getter
	@Setter
	@NotBlank(message = "evtNo는 필수 값입니다.")
	private String evtNo = "";

	@Getter
	@Setter
	private String sort = ""; // 최신순(newest)  / 할인율(dscntUp)  /  가격 낮은순(priceDw) / 가격 높은순(priceUp)

	@Getter
	@Setter
	private String amountFrom = "";

	@Getter
	@Setter
	private String amountTo = "";

	@Getter
	@Setter
	private String storeCode = "";

	@Getter
	@Setter
	private String accessToken = "";

}