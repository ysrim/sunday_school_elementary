package net.api.enotary.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResDocInfoVO {

	public ResDocInfoVO() {
	}

	public ResDocInfoVO(String applcntIdntfr, String docHash, String createTime) {
		this.applcntIdntfr = applcntIdntfr;
		this.docHash = docHash;
		this.createTime = createTime;
	}

	private String applcntIdntfr; // 촉탁신청자 식별자
	private String docHash; // 문서 해쉬 정보
	private String createTime; // 작성시간, 양식(1970-01-01 00:00:01)

}