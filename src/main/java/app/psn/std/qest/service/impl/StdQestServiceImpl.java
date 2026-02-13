package app.psn.std.qest.service.impl;

import app.psn.com.service.DomainService;
import app.psn.com.vo.QuestVO;
import app.psn.std.qest.mapper.StdQestMapper;
import app.psn.std.qest.service.StdQestService;
import app.psn.std.qest.vo.StdQestListVO;
import app.psn.std.qest.vo.StdQestPendingVO;
import com.base.utl.SessionUtil;
import com.base.vo.QuestCompleteEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service("stdQestService")
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StdQestServiceImpl implements StdQestService {

	private final ApplicationEventPublisher publisher;

	private final StdQestMapper stdQestMapper;

	private final DomainService domainService;

	@Override
	public List<StdQestListVO> sltQestList() {

		return stdQestMapper.sltQestList(SessionUtil.getStdMberInfo().mberSn() + "");

	}

	public void qestDo(StdQestPendingVO stdQestPendingVO) {

		QuestVO questVO = domainService.sltQuest(stdQestPendingVO.getQuestSn());

		if (stdQestMapper.qestDo(stdQestPendingVO) < 1) { // 비즈니스 로직상 필수라면 예외 처리
			throw new RuntimeException("퀘스트 수행 내역 저장 중 오류가 발생했습니다.");
		}

		log.warn("questVO => {}", questVO);

		if ("Y".equals(questVO.immediatePayYn())) { // 퀘스트가 즉시 보상이면 바로 보상
			publisher.publishEvent(new QuestCompleteEvent(stdQestPendingVO.getMberSn(), stdQestPendingVO.getQuestSn(), stdQestPendingVO.getLogSn()));
		}

	}

	@Override
	public boolean qestCompleteChk(StdQestPendingVO stdQestPendingVO) {

		return stdQestMapper.qestCompleteChk(stdQestPendingVO) > 0;

	}

}