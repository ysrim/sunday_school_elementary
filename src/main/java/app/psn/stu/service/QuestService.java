package app.psn.stu.service;

import java.util.List;

import app.psn.stu.vo.QuestListVO;
import app.psn.stu.vo.QuestPendingVO;

public interface QuestService {

	List<QuestListVO> sltQuestList(); // 길드원 목록

	void questDo(QuestPendingVO questVO);

	boolean questCompleteChk(QuestPendingVO questVO);

}