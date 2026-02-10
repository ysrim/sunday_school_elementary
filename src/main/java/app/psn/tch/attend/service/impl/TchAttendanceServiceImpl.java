package app.psn.tch.attend.service.impl;

import app.psn.std.quest.service.StdQuestService;
import app.psn.tch.attend.mapper.TchAttendanceMapper;
import app.psn.tch.attend.service.TchAttendanceService;

import app.psn.tch.quest.service.TchQuestService;
import app.psn.tch.quest.vo.ReqQuestProcVO;
import com.base.utl.SessionUtil;
import com.base.utl.StringUtil;

import app.psn.tch.attend.vo.AtndApprovalVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service("tchAttendanceService")
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TchAttendanceServiceImpl implements TchAttendanceService {

    private final TchAttendanceMapper tchAttendanceMapper;

    private final TchQuestService tchQuestService;

    @Override
    public List<AtndApprovalVO> sltReqAtndList(String reqDate) {

        return tchAttendanceMapper.sltReqAtndList(SessionUtil.getTchMberInfo().guildSn(), reqDate);

    }

    @Override
    public void atndChk(ReqQuestProcVO reqQuestProcVO) {

        tchQuestService.questProc(reqQuestProcVO);

    }

}