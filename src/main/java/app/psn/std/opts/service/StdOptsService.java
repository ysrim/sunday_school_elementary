package app.psn.std.opts.service;

import app.psn.std.opts.vo.StdRewardHistoryVO;

import java.util.HashMap;
import java.util.List;

public interface StdOptsService {

	/**
	 * QR코드 정보 문자열
	 */
	String QrCodeStr();

	/**
	 * 패스워드 수정 요청
	 */
	void pwChg(String currentPw, String newPw);

	/**
	 * 누적 경험치 포인트 적립내역 목록
	 */
	List<StdRewardHistoryVO> sltRewardHistory();

	/**
	 * 주간 경험치, 포인트 적립내역 통계
	 */
	HashMap<String, Integer> sltWeekStatics();

}