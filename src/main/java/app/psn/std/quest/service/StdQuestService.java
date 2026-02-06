package app.psn.std.quest.service;

import java.util.List;

import app.psn.std.quest.vo.StdQuestListVO;
import app.psn.std.quest.vo.StdQuestPendingVO;

public interface StdQuestService {

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