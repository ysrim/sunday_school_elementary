package app.psn.tch.quest.service;

import app.psn.tch.quest.vo.ReqQuestProcVO;
import app.psn.tch.quest.vo.StdQuestListVO;
import app.psn.tch.quest.vo.StdQuestPendingVO;

import java.util.List;

public interface TchQuestService {

    /**
     * 길드원 목록
     */
    List<StdQuestListVO> sltQuestList();

    /**
     * 퀘스트 승인
     */
    void questProc(ReqQuestProcVO reqQuestProcVO);

    /**
     * 퀘스트 완료 체크
     */
    boolean questCompleteChk(StdQuestPendingVO questVO);

}