package app.psn.mng.qest.service.impl;

import java.util.List;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.base.enumm.com.QuestLogStatusEnum;
import com.base.vo.QuestCompleteEvent;

import app.psn.com.service.DomainService;
import app.psn.com.vo.QuestLogsVO;
import app.psn.mng.qest.mapper.MngQestMapper;
import app.psn.mng.qest.service.MngQestService;
import app.psn.mng.qest.vo.MngQestVO;
import app.psn.mng.qest.vo.MngReqQuestProcVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("mngQestService")
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MngQestServiceImpl implements MngQestService {

	private final MngQestMapper mngQestMapper;

	private final ApplicationEventPublisher publisher;

	private final DomainService domainService;

	@Override
	public List<MngQestVO> getQestLogList() {
		return mngQestMapper.getQestLogList();
	}

	@Override
	public void questProc(MngReqQuestProcVO mngReqQuestProcVO) {

		// 1. 퀘스트 승인 처리
		mngQestMapper.questProc(mngReqQuestProcVO);

		// 2. 승인인 경우에는 보상지급
		if (QuestLogStatusEnum.APPROVED.isSameStatus(mngReqQuestProcVO.getStatus())) {
			QuestLogsVO questLogsVO = domainService.sltQuestLogs(mngReqQuestProcVO.getLogSn());
			publisher.publishEvent(new QuestCompleteEvent(questLogsVO.mberSn(), questLogsVO.questSn(), questLogsVO.logSn()));
		}

	}

}