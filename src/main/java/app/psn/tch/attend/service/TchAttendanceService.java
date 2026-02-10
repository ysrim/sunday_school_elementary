package app.psn.tch.attend.service;

import java.util.List;

import app.psn.tch.attend.vo.AtndApprovalVO;
import app.psn.tch.quest.vo.ReqQuestProcVO;

public interface TchAttendanceService {

    /**
     * 출석 승인요청 목록
     */
    List<AtndApprovalVO> sltReqAtndList(String reqDate);

    /**
     * 출석요청
     */
    void atndChk(ReqQuestProcVO reqQuestProcVO);

}