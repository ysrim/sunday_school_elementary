package app.psn.std.quest.service.impl;

import java.util.List;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.base.utl.SessionUtil;
import com.base.vo.QuestCompleteEvent;

import app.psn.com.service.DomainService;
import app.psn.com.vo.QuestVO;
import app.psn.std.quest.mapper.QuestMapper;
import app.psn.std.quest.service.QuestService;
import app.psn.std.quest.vo.QuestListVO;
import app.psn.std.quest.vo.QuestPendingVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("questService")
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QuestServiceImpl implements QuestService {

	private final ApplicationEventPublisher publisher;

	private final QuestMapper questMapper;

	private final DomainService domainService;

	@Override
	public List<QuestListVO> sltQuestList() {

		return questMapper.sltQuestList(SessionUtil.getMberInfo().mberSn() + "");

	}

	public void questDo(QuestPendingVO questPendingVO) {

		QuestVO questVO = domainService.sltQuest(questPendingVO.getQuestSn());

		if (questMapper.questDo(questPendingVO) < 1) { // 비즈니스 로직상 필수라면 예외 처리
			throw new RuntimeException("퀘스트 수행 내역 저장 중 오류가 발생했습니다.");
		}

		log.warn("questVO => {}", questVO);

		if ("Y".equals(questVO.immediatePayYn())) { // 퀘스트가 즉시 보상이면 바로 보상
			publisher.publishEvent(new QuestCompleteEvent(questPendingVO.getMberSn(), questPendingVO.getQuestSn(), questPendingVO.getLogSn()));
		}

	}

	@Override
	public boolean questCompleteChk(QuestPendingVO questVO) {

		return questMapper.questCompleteChk(questVO) > 0 ? true : false;

	}

}