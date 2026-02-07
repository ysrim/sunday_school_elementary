package app.psn.tch.quest.service.impl;

import app.psn.com.service.DomainService;
import app.psn.com.vo.QuestVO;
import app.psn.tch.quest.mapper.TchQuestMapper;
import app.psn.tch.quest.service.TchQuestService;
import app.psn.tch.quest.vo.StdQuestListVO;
import app.psn.tch.quest.vo.StdQuestPendingVO;
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

    public void questDo(StdQuestPendingVO stdQuestPendingVO) {

        QuestVO questVO = domainService.sltQuest(stdQuestPendingVO.getQuestSn());

        if (tchQuestMapper.questDo(stdQuestPendingVO) < 1) { // 비즈니스 로직상 필수라면 예외 처리
            throw new RuntimeException("퀘스트 수행 내역 저장 중 오류가 발생했습니다.");
        }

        log.warn("questVO => {}", questVO);

        if ("Y".equals(questVO.immediatePayYn())) { // 퀘스트가 즉시 보상이면 바로 보상
            publisher.publishEvent(new QuestCompleteEvent(stdQuestPendingVO.getMberSn(), stdQuestPendingVO.getQuestSn(), stdQuestPendingVO.getLogSn()));
        }

    }

    @Override
    public boolean questCompleteChk(StdQuestPendingVO questVO) {

        return tchQuestMapper.questCompleteChk(questVO) > 0;

    }

}