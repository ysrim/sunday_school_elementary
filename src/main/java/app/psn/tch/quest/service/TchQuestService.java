package app.psn.tch.quest.service;

import app.psn.tch.quest.vo.ReqQuestProcVO;
import app.psn.tch.quest.vo.TchReqQestVO;

import java.util.List;

public interface TchQuestService {

	/**
	 * 퀘스트 요청 목록
	 */
	List<TchReqQestVO> sltReqQestList(String reqMonth);

	/**
	 * 퀘스트 승인
	 */
	void questProc(ReqQuestProcVO reqQuestProcVO);

}