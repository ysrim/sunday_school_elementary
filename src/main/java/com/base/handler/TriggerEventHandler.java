package com.base.handler;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.base.vo.AvatarLvlUdtEvent;
import com.base.vo.QuestCompleteEvent;

import app.psn.com.service.DomainService;
import app.psn.com.service.RewardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class TriggerEventHandler {

	private final DomainService domainService;
	private final RewardService rewardService;

	/**
	 * 퀘스트를 완료 시 보상을 부여한다.
	 * @param event
	 */
	@EventListener
	public void handleQuestCompleteEvent(QuestCompleteEvent event) {

		log.warn("Quest Complete Process Started: mberSn={}, questSn={}, logSn={}", event.mberSn(), event.questSn(), event.logSn());

		//AvatarVO mberVO = domainService.sltAvatar(event.mberSn());

		/**
		 * <pre>
		 * 퀘스트를 완료 시 보상을 부여한다.
		 * 1. QUESTS테이블의 명시된 경험치와 달란트를 회원에게 지급
		 * 2. 퀘스트를 연속으로 수행할 경우 QUEST_CONTINUITY_RULES테이블 정의된 일자만큼 수행할 경우 경험치와 달란트 보상
		 * 3. 최종누적된 경험치를 바탕으로 아바타 레벨업유무 결정
		 * </pre>
		 */
		rewardService.processQuestRewards(event); // 아바타정보에 포인트 경험치 업데이트 트리거 매소드 토스트 메시지 저장

	}

	/**
	 * 회원의 아바타 레벨을 갱신한다.
	 * @param event
	 */
	@EventListener
	public void handleAvatarLvlUdtEvent(AvatarLvlUdtEvent event) {

		/**
		 * <pre>
		 * 회원의 아바타 레벨을 갱신한다.
		 * 1. 회원의 아바타 레벨 정보를 가져온다.
		 * 2. 회원의 누적 경험치를
		 * </pre>
		 */

	}

	/**
	 * // 리워드가 지급되면 토스트 매시지로 알림을 준다.
	 * 토스트 메시지 (input: mberSn, notiType, message, emoji)-> serviceImple 구현체에 하는 일
	 * 입력값을 바탕으로 테이블에 인서트 끝
	 * => 트리거 동작 조건: void insMberRewardLogs(RewardVO reward) 정보가 입력되었을 때, 레벨업 했을 경우
	 */

}
