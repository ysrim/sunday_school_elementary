package app.psn.std.qest.service;

import java.util.List;

import app.psn.std.qest.vo.StdQestListVO;
import app.psn.std.qest.vo.StdQestPendingVO;

public interface StdQestService {

	/**
	 * 길드원 목록
	 */
	List<StdQestListVO> sltQestList();

	/**
	 * 퀘스트 수행 요청
	 */
	void qestDo(StdQestPendingVO stdQestPendingVO);

	/**
	 * 퀘스트 완료 체크
	 */
	boolean qestCompleteChk(StdQestPendingVO stdQestPendingVO);

}