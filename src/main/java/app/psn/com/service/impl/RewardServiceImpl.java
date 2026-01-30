package app.psn.com.service.impl;

import com.base.utl.StringUtil;
import com.base.vo.QuestCompleteEvent;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.psn.com.mapper.RewardMapper;
import app.psn.com.service.DomainService;
import app.psn.com.service.RewardService;
import app.psn.com.vo.AvatarVO;
import app.psn.com.vo.QuestContinuityRulesVO;
import app.psn.com.vo.QuestContinuityVO;
import app.psn.com.vo.RewardVO;
import app.psn.com.vo.QuestVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("rewardService")
@RequiredArgsConstructor // 1. final 필드에 대한 생성자를 자동 생성 (생성자 주입)
@Transactional(readOnly = true) // 2. 기본적으로 읽기 전용으로 설정 (성능 최적화)
public class RewardServiceImpl implements RewardService {

	private final RewardMapper rewardMapper;

	private final DomainService domainService;

	@Override
	public void insMberRewardLogs(RewardVO reward) {
		rewardMapper.insMberRewardLogs(reward);
	}

	@Override
	public void insQuestContinuity(QuestContinuityVO questContinuityVO) {
		rewardMapper.insQuestContinuity(questContinuityVO);
	}

	/**
	 * 퀘스트를 수행했을 경우 보상하는 프로세스
	 * @param event
	 */
	@Override
	@Transactional
	public void processQuestRewards(QuestCompleteEvent event) {

		// 1. 퀘스트 정보(지급할 달란트와 경험치 가져오기)
		QuestVO quest = domainService.sltQuest(event.questSn());
		// 2. 달란트 지급
		this.insMberRewardLogs(RewardVO.ofPoint(event, quest));
		// 3. 경험치 지급
		this.insMberRewardLogs(RewardVO.ofExp(event, quest));

		// 4. 연퀘 체크 및 추가 보상
		// 4.1. QUEST_CONTINUITY_RULES 수행하는 퀘스트가 연퀘가 가능한지 여부 판단.
		QuestContinuityRulesVO urles = domainService.sltQuestContinuityRules(event.questSn());
		if (urles != null) {
			handleQuestContinuity(event, urles);
		}

	}

	private void handleQuestContinuity(QuestCompleteEvent event, QuestContinuityRulesVO rules) {

		int newStreak = 1;
		QuestContinuityVO currentStreak = domainService.sltQuestContinuity(event.mberSn(), event.questSn());

		if (currentStreak != null) {
			newStreak = StringUtil.getDateDiffToday(currentStreak.lastSuccessDe()) ? currentStreak.currentStreak() + 1 : 1;
		}

		// 목표(rules.continuityDays)에 도달했는지 확인
		if (newStreak >= rules.continuityDays()) {
			log.info("연속 퀘스트 목표 달성! 보상 지급. Streak: {}", newStreak);
			// TODO: 실제 보상 지급 로직 호출 (예: insMberRewardLogs 사용)
			newStreak = 0;
		}

		this.insQuestContinuity(QuestContinuityVO.ofIns(event, newStreak));
	}

}