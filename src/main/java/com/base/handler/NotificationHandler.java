package com.base.handler;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.base.vo.QuestCompleteEvent;

import app.psn.com.mapper.RewardMapper;
import app.psn.com.service.RewardService;
import app.psn.com.vo.RewardVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class NotificationHandler {

	private final RewardService rewardService;

	@EventListener
	public void handleQuestEvent(QuestCompleteEvent event) {

		log.warn("mberSn  => {}", event.MberSn());
		log.warn("questSn => {}", event.questSn());

		rewardService.giveRewards(new RewardVO(event.MberSn(), event.questSn()));

		// 퀘스트 순번을 통해 리워드 정보를 받아온다.
		/**
		 4. 학생순번, 퀘스트순번을 활용하여 포인트, 경험치 수치를 찾아해당 학생에게 포인트와 경험치를 넣어준다.
		 > 경험치 수치를 업데이트하는 것은 레벨업과 관련 되어있음... 레벨업되면 달란트도 지급함..;;
		 > 보너스 경험치도 있음 이것 또한 고려해야함.
		 5. 마지막으로 해당 학생에게 토스트DB에 데이너 넣는다.
		 */
	}
}
