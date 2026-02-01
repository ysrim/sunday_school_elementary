package app.psn.com.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.psn.com.mapper.DomainMapper;
import app.psn.com.service.DomainService;
import app.psn.com.vo.AvatarLevelRulesVO;
import app.psn.com.vo.AvatarVO;
import app.psn.com.vo.QuestContinuityRulesVO;
import app.psn.com.vo.QuestContinuityVO;
import app.psn.com.vo.QuestVO;
import app.psn.com.vo.ToastMsgVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("domainService")
@RequiredArgsConstructor // 1. final 필드에 대한 생성자를 자동 생성 (생성자 주입)
@Transactional(readOnly = true) // 2. 기본적으로 읽기 전용으로 설정 (성능 최적화)
public class DomainServiceImpl implements DomainService {

	private final DomainMapper domainMapper;

	@Override
	public QuestVO sltQuest(int questSn) {
		return domainMapper.sltQuest(questSn);
	}

	@Override
	public AvatarVO sltAvatar(int mberSn) {
		return domainMapper.sltAvatar(mberSn);
	}

	@Override
	public AvatarLevelRulesVO sltAvatarLevelRules(int level) {
		return domainMapper.sltAvatarLevelRules(level);
	}

	@Override
	public QuestContinuityRulesVO sltQuestContinuityRules(int questSn) {
		return domainMapper.sltQuestContinuityRules(questSn);
	}

	@Override
	public QuestContinuityVO sltQuestContinuity(int mberSn, int questSn) {
		return domainMapper.sltQuestContinuity(mberSn, questSn);
	}

}