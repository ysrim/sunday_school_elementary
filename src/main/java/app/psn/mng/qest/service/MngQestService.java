package app.psn.mng.qest.service;

import java.util.List;

import app.psn.mng.qest.vo.MngQestVO;
import app.psn.mng.qest.vo.MngReqQuestProcVO;

public interface MngQestService {

	/**
	 * 퀘스트 수행 목록
	 */
	List<MngQestVO> getQestLogList();

	/**
	 * 퀘스트 승인
	 */
	void questProc(MngReqQuestProcVO mngReqQuestProcVO);

}