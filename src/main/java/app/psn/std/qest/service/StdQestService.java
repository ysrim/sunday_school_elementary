package app.psn.std.qest.service;

import app.psn.std.qest.vo.StdQestVO;
import app.psn.std.qest.vo.StdQestPendingVO;

import java.util.List;

public interface StdQestService {

	/**
	 * 길드원 목록
	 */
	List<StdQestVO> sltQestList();

	/**
	 * 퀘스트 수행 요청
	 */
	void qestDo(StdQestPendingVO stdQestPendingVO);

	/**
	 * 퀘스트 완료 체크
	 */
	boolean qestCompleteChk(StdQestPendingVO stdQestPendingVO);

}