package net.api.enotary.vo;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResEltsgnInfoVO {

	private EttffVO ettffReprsnt; // 대표 촉탁인 서명정보
	private List<EttffVO> ettffPartcptn; // 참여 촉탁인 서명정보
	private EttffVO ntratPsn; // 공증인 서명정보
	private List<EttffVO> ettffEtc; // 기타 참여인 서명정보

}