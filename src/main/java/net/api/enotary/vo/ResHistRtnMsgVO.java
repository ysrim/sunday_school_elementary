package net.api.enotary.vo;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResHistRtnMsgVO {

	private String reqstDocIdntfr;
	private String reqstStep;
	private String useYn;
	private ResDocInfoVO docInfo; // 문서 정보
	private ResEltsgnInfoVO eltsgnInfo; // 서명정보
	private List<PblicteVO> pblicteHist; // 발행정보

}