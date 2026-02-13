package app.psn.com.service.impl;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.base.enumm.com.RewardTypeEnum;
import com.base.enumm.com.ToastTypeEnum;
import com.base.utl.CommonUtil;
import com.base.vo.AvatarLvlUdtEvent;
import com.base.vo.QuestCompleteEvent;
import com.base.vo.ToastMsgEvent;

import app.psn.com.mapper.RewardMapper;
import app.psn.com.service.DomainService;
import app.psn.com.service.RewardService;
import app.psn.com.vo.QuestContinuityRulesVO;
import app.psn.com.vo.QuestContinuityVO;
import app.psn.com.vo.QuestVO;
import app.psn.com.vo.RewardVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("rewardService")
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RewardServiceImpl implements RewardService {

	private final RewardMapper rewardMapper;

	private final DomainService domainService;

	private final ApplicationEventPublisher publisher;

	/**
	 * 회원에게 리워드(경험치, 달란트)를 지급한다.
	 */
	@Override
	@Transactional
	public void insMberReward(RewardVO reward) {

		if (reward.amount() == 0) {
			return;
		}
		rewardMapper.insMberRewardLogs(reward);  // 회원에게 리워드 테이블에 (경험치, 달란트)를 지급한다.
		rewardMapper.updateAvatarAmount(reward); // 회원 아바타 정보(포인트, 경험치) 실제 업데이트 로직

		// 토스트 메시지 발송
		String rewardType = RewardTypeEnum.valueOf(reward.rewardType()).getRewardName();
		String toastMsg = rewardType + "보상으로 " + reward.amount() + " " + reward.rewardType() + " 들어왔어요.";
		publisher.publishEvent(new ToastMsgEvent(reward.mberSn(), ToastTypeEnum.REWARD.toString(), reward.description(), toastMsg));

	}

	/**
	 * 연퀘 수행이력정보를 담는다.
	 */
	@Override
	public void insQuestContinuity(QuestContinuityVO questContinuityVO) {
		rewardMapper.insQuestContinuity(questContinuityVO);
	}

	/**
	 * 퀘스트를 수행했을 경우 보상하는 프로세스
	 */
	@Override
	@Transactional
	public void processQuestRewards(QuestCompleteEvent event) {

		// 1. 퀘스트 정보(지급할 달란트와 경험치 가져오기)
		QuestVO quest = domainService.sltQuest(event.questSn());

		// 2. 달란트 지급
		this.insMberReward(RewardVO.ofQuestPoint(event, quest));

		// 3. 경험치 지급
		this.insMberReward(RewardVO.ofQuestExp(event, quest));

		// 4. 연퀘퀘스트로그 저장 및 연퀘 조건 만족 시 추가 보상
		QuestContinuityRulesVO rules = domainService.sltQuestContinuityRules(event.questSn());
		if (rules != null) { // QUEST_CONTINUITY_RULES 수행하는 퀘스트가 연퀘가 가능한지 여부 판단. 연퀘보상정보가 없으면 pass
			handleQuestContinuity(event, rules);
		}

		// 5. 등록한 경험치 검증 핸들러
		publisher.publishEvent(new AvatarLvlUdtEvent(event.mberSn()));

	}

	/**
	 * 연속퀘스트 추가 보상 로직
	 */
	private void handleQuestContinuity(QuestCompleteEvent event, QuestContinuityRulesVO rules) {

		// 1. 연속퀘스트 수행 일수 카운트
		Integer newStreak = 1;

		// 2. 회원이 수행하고 있는 연속퀘스트 수행이력 정보 받아오기
		QuestContinuityVO currentStreak = domainService.sltQuestContinuity(event.mberSn(), event.questSn());

		// 3. 수행이력 정보가 있고 어제일자로 동일한 퀘스트 수행이력이 있으면 newStreak변수에 +1을 해준다.
		if (currentStreak != null) {
			newStreak = CommonUtil.getDateDiffToday(currentStreak.lastSuccessDe()) ? (currentStreak.currentStreak() + 1) : 1;
		}

		// 4. 수행일수가 연속퀘스트 일자에 맞으면 추가 보상을 해주고 수행일 수를 0으로 바꾼다.
		if (newStreak >= rules.continuityDays()) { // 목표(rules.continuityDays)에 도달했는지 확인
			log.debug("연속 퀘스트 목표 달성! 보상 지급. mberSn: {}, questSn: {}", event.mberSn(), event.questSn());
			this.insMberReward(RewardVO.ofContinuityPoint(event, rules)); // 달란트 획득
			this.insMberReward(RewardVO.ofContinuityExp(event, rules)); // 경험치 획득
			newStreak = 0;
		}

		// 5. 연속퀘스트 수행이력 테이블에 merge한다.
		this.insQuestContinuity(QuestContinuityVO.ofIns(event, newStreak));

	}

}