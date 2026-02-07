package app.psn.tch.quest.service;

import app.psn.tch.quest.vo.StdQuestListVO;
import app.psn.tch.quest.vo.StdQuestPendingVO;

import java.util.List;

public interface TchQuestService {

	/**
	 * 길드원 목록
	 */
	List<StdQuestListVO> sltQuestList();

	/**
	 * 퀘스트 수행 요청
	 */
	void questDo(StdQuestPendingVO questVO);

	/**
	 * 퀘스트 완료 체크
	 */
	boolean questCompleteChk(StdQuestPendingVO questVO);

}