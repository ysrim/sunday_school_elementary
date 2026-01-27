package app.psn.stu.service;

import java.util.List;

import app.psn.stu.vo.HomeGuildListVO;
import app.psn.stu.vo.QuestListVO;
import app.psn.stu.vo.QuestVO;

public interface QuestService {

	List<QuestListVO> sltQuestList(); // 길드원 목록

	boolean questDo(QuestVO questVO);

}