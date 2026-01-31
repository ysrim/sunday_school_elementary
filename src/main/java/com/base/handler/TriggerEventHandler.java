package com.base.handler;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.base.vo.AvatarLvlUdtEvent;
import com.base.vo.QuestCompleteEvent;
import com.base.vo.ToastMsgEvent;

import app.psn.com.service.AvatarService;
import app.psn.com.service.RewardService;
import app.psn.com.service.ToastMsgService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class TriggerEventHandler {

	private final RewardService rewardService;

	private final AvatarService avatarService;

	private final ToastMsgService toastMsgService;

	/**
	 * 퀘스트를 완료 후 보상 지급
	 * (선생님이 승인 후 보상지급)
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
	 * (회원의 exp가 변경되었을 경우에 수행)
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
		avatarService.udtAvatarLevel(event.mberSn());

	}

	/**
	 * 회원별 토스트 메시지를 저장한다.
	 */
	@EventListener
	public void handleToastMsgEvent(ToastMsgEvent event) {

		toastMsgService.insToastMsg(event);

	}

}