package app.psn.std.atnd.service;

import app.psn.std.atnd.vo.StdAtndVO;

import java.util.List;

public interface StdAtndService {

	/**
	 * 당월 주일 출석 목록
	 */
	List<StdAtndVO> sltAtndList();

	/**
	 * 연속출석일 수
	 */
	Integer sltCtnuAtndCnt();

	/**
	 * 출석요청
	 */
	void atndDo();

}