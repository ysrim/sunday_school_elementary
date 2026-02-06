package app.psn.std.quest.service.impl;

import java.util.List;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.base.utl.SessionUtil;
import com.base.vo.QuestCompleteEvent;

import app.psn.com.service.DomainService;
import app.psn.com.vo.QuestVO;
import app.psn.std.quest.mapper.StdQuestMapper;
import app.psn.std.quest.service.StdQuestService;
import app.psn.std.quest.vo.StdQuestListVO;
import app.psn.std.quest.vo.StdQuestPendingVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("stdQuestService")
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StdQuestServiceImpl implements StdQuestService {

	private final ApplicationEventPublisher publisher;

	private final StdQuestMapper stdQuestMapper;

	private final DomainService domainService;

	@Override
	public List<StdQuestListVO> sltQuestList() {

		return stdQuestMapper.sltQuestList(SessionUtil.getStdMberInfo().mberSn() + "");

	}

	public void questDo(StdQuestPendingVO stdQuestPendingVO) {

		QuestVO questVO = domainService.sltQuest(stdQuestPendingVO.getQuestSn());

		if (stdQuestMapper.questDo(stdQuestPendingVO) < 1) { // 비즈니스 로직상 필수라면 예외 처리
			throw new RuntimeException("퀘스트 수행 내역 저장 중 오류가 발생했습니다.");
		}

		log.warn("questVO => {}", questVO);

		if ("Y".equals(questVO.immediatePayYn())) { // 퀘스트가 즉시 보상이면 바로 보상
			publisher.publishEvent(new QuestCompleteEvent(stdQuestPendingVO.getMberSn(), stdQuestPendingVO.getQuestSn(), stdQuestPendingVO.getLogSn()));
		}

	}

	@Override
	public boolean questCompleteChk(StdQuestPendingVO questVO) {

		return stdQuestMapper.questCompleteChk(questVO) > 0;

	}

}