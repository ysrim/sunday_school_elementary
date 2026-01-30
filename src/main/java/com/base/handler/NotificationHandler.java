package com.base.handler;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.base.vo.QuestCompleteEvent;

import app.psn.com.service.DomainService;
import app.psn.com.service.RewardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class NotificationHandler {

	private final DomainService domainService;
	private final RewardService rewardService;

	/**
	 * 회원이 퀘스트를 완료 했을 경우 실행되는 매서드(입력값 회원Sn, 퀘스트SN)
	 *
	 * @param event
	 */
	@EventListener
	public void handleQuestEvent(QuestCompleteEvent event) {

		log.warn("Quest Complete Process Started: mberSn={}, questSn={}, logSn={}", event.mberSn(), event.questSn(), event.logSn());

		//AvatarVO mberVO = domainService.sltAvatar(event.mberSn());

		// 1. 리워드 지급 (포인트 & 경험치)
		rewardService.processQuestRewards(event); // 아바타정보에 포인트 경험치 업데이트 트리거 매소드 토스트 메시지 저장

		/**
		 * <pre>
		 * 트리거 매소드 로직
		 *  가. 레벨업 판단 처리(input: mberSn) -> serviceImple 구현체에 하는 일
		 *     - 회원정보를 입력 받으면 MBER_REWARD_LOGS테이블의 마지막 누적경험치 수치를 가져온다.
		 *     - LEVEL_RULES테이블의 누적경험치를 대입하여 예상 레벨추출.
		 *     - 레벨추출한 수치와 AVATAR_INFO의 레벨과 비교해 상승 여부를 판단
		 *     - 상승되었으면 MBER_REWARD_LOGS테이블에 데이터추가해준다.
		 *     => 트리거 동작 조건: 퀘스트 완료 했을 경우
		 *
		 *   나. 토스트 메시지 (input: mberSn, notiType, message, emoji)-> serviceImple 구현체에 하는 일
		 *     입력값을 바탕으로 테이블에 인서트 끝
		 *     => 트리거 동작 조건: void insMberRewardLogs(RewardVO reward) 정보가 입력되었을 때, 레벨업 했을 경우
		 *
		 * </pre>
		 */

	}

	@EventListener
	public void handleAvatarEvent(QuestCompleteEvent event) {

	}
}
