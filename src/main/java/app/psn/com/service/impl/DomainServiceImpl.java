package app.psn.com.service.impl;

import app.psn.com.vo.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.psn.com.mapper.DomainMapper;
import app.psn.com.service.DomainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("domainService")
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DomainServiceImpl implements DomainService {

	private final DomainMapper domainMapper;

	@Override
	public QuestVO sltQuest(Integer questSn) {

		return domainMapper.sltQuest(questSn);

	}

	@Override
	public AvatarVO sltAvatar(Integer mberSn) {

		return domainMapper.sltAvatar(mberSn);

	}

	@Override
	public AvatarLevelRulesVO sltAvatarLevelRules(Integer level) {

		return domainMapper.sltAvatarLevelRules(level);

	}

	@Override
	public QuestContinuityRulesVO sltQuestContinuityRules(Integer questSn) {

		return domainMapper.sltQuestContinuityRules(questSn);

	}

	@Override
	public QuestContinuityVO sltQuestContinuity(Integer mberSn, Integer questSn) {

		return domainMapper.sltQuestContinuity(mberSn, questSn);

	}

	@Override
	public QuestLogsVO sltQuestLogs(Integer logSn) {

		return domainMapper.sltQuestLogs(logSn);

	}

}