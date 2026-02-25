package app.psn.tch.qest.service.impl;

import app.psn.com.service.DomainService;
import app.psn.com.vo.QuestLogsVO;
import app.psn.tch.qest.mapper.TchQestMapper;
import app.psn.tch.qest.service.TchQestService;
import app.psn.tch.qest.vo.ReqQestProcVO;
import app.psn.tch.qest.vo.TchResQestVO;
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
public class TchQestServiceImpl implements TchQestService {

	private final ApplicationEventPublisher publisher;

	private final TchQestMapper tchQestMapper;

	private final DomainService domainService;

	@Override
	public List<TchResQestVO> sltReqQestList(String reqMonth) {
		return tchQestMapper.sltReqQestList(SessionUtil.getTchMberInfo().guildSn(), reqMonth);
	}

	@Override
	public void questProc(ReqQestProcVO reqQestProcVO) {

		// 1. 퀘스트 승인 처리
		tchQestMapper.questProc(reqQestProcVO);

		// 2. 승인인 경우에는 보상지급
		if (QuestLogStatusEnum.APPROVED.isSameStatus(reqQestProcVO.getStatus())) {
			QuestLogsVO questLogsVO = domainService.sltQuestLogs(reqQestProcVO.getLogSn());
			publisher.publishEvent(new QuestCompleteEvent(questLogsVO.mberSn(), questLogsVO.questSn(), questLogsVO.logSn()));
		}

	}

}