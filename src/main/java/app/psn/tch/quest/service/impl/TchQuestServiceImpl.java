package app.psn.tch.quest.service.impl;

import app.psn.com.service.DomainService;
import app.psn.com.vo.QuestLogsVO;
import app.psn.tch.quest.mapper.TchQuestMapper;
import app.psn.tch.quest.service.TchQuestService;
import app.psn.tch.quest.vo.ReqQuestProcVO;
import app.psn.tch.quest.vo.StdQuestListVO;
import app.psn.tch.quest.vo.StdQuestPendingVO;
import com.base.enumm.com.QuestLogStatusEnum;
import com.base.utl.SessionUtil;
import com.base.vo.QuestCompleteEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service("tchQuestService")
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TchQuestServiceImpl implements TchQuestService {

    private final ApplicationEventPublisher publisher;

    private final TchQuestMapper tchQuestMapper;

    private final DomainService domainService;

    @Override
    public List<StdQuestListVO> sltQuestList() {

        return tchQuestMapper.sltQuestList(SessionUtil.getStdMberInfo().guildSn() + "");

    }

    public void questProc(ReqQuestProcVO reqQuestProcVO) {

        // 1. 퀘스트 승인 처리
        tchQuestMapper.questProc(reqQuestProcVO);

        // 2. 승인인 경우에는 보상지급
        if (QuestLogStatusEnum.APPROVED.isSameStatus(reqQuestProcVO.getStatus())) {
            QuestLogsVO questLogsVO = domainService.sltQuestLogs(reqQuestProcVO.getLogSn());
            publisher.publishEvent(new QuestCompleteEvent(questLogsVO.mberSn(), questLogsVO.questSn(), questLogsVO.logSn()));
        }

    }

}