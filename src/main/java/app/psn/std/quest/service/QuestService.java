package app.psn.std.quest.service;

import java.util.List;

import app.psn.std.quest.vo.QuestListVO;
import app.psn.std.quest.vo.QuestPendingVO;

public interface QuestService {

    /**
     * 길드원 목록
     */
    List<QuestListVO> sltQuestList();

    /**
     * 퀘스트 수행 요청
     *
     * @param questVO
     */
    void questDo(QuestPendingVO questVO);

    /**
     * 퀘스트 완료 체크
     *
     * @param questVO
     * @return
     */
    boolean questCompleteChk(QuestPendingVO questVO);

}