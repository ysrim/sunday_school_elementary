package com.base.handler;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.base.enumm.RewardGetPathEnum;
import com.base.enumm.RewardTypeEnum;
import com.base.vo.QuestCompleteEvent;

import app.psn.com.mapper.RewardMapper;
import app.psn.com.service.DomainService;
import app.psn.com.service.RewardService;
import app.psn.com.vo.AvatarVO;
import app.psn.com.vo.QuestVO;
import app.psn.com.vo.RewardVO;
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
	 * @param event
	 */
	@EventListener
	public void handleQuestEvent(QuestCompleteEvent event) {

		log.warn("mberSn  => {}", event.mberSn());
		log.warn("questSn => {}", event.questSn());
		log.warn("logSn => {}", event.logSn());

		// 1. 퀘스트SN을 통해 경험치, 달란트 값을 가져온다.
		QuestVO questVO = domainService.sltQuest(event.questSn());
		// 2. 회원의 현재 상태의 포인트와 경험치정보를 가져온다.
		AvatarVO mberVO = domainService.sltAvatar(event.mberSn());
		// 3. 가져온 값을 리워드 테이블에 인서트 한다.
		rewardService.giveRewards( // 3.1. 포인트 로그
			new RewardVO( // 리워드
				event.mberSn(), //
				RewardTypeEnum.POINT.toString(), //
				questVO.rewardPoint(), //
				RewardGetPathEnum.QUEST.toString(), //
				event.logSn(), //
				questVO.description())//
		);
		// 3. 가져온 값을 리워드 테이블에 인서트 한다.
		rewardService.giveRewards( // 3.2. 경험치 로그
			new RewardVO( // 리워드
				event.mberSn(), //
				RewardTypeEnum.EXP.toString(), //
				questVO.rewardExp(), //
				RewardGetPathEnum.QUEST.toString(), //
				event.logSn(), //
				questVO.description())//
		);

		// 5. 아바타정보에 최종 리워드를 업데이트해준다..
		//     >> 4.1. 트리거 매소드 C실행: 연속 퀘스트 수행시 달란트, 경험치추가 부여 트리거 매소드 실행
		//     >> 4.2. 트리거 매소드 B실행: 랩업할 경우 + 달란트 부여

		/**
		 * B. 회원이 래밸업할 경우(회원SN)
		 */
		// 1. 아바타정보의 경험치 정보가 변경될 경우 실행해야함
		// 2. 누적 경험치가 래밸테이블 정보와 비교한다. 같으면 아무일도 안일어남 틀리면 아바타정보 레벨 정보를 업데이트
		// 3. 해당레벨 정보에 기재된 달란트 점수를 부여한다.
		//    > 달란트 로그 테이블에 저장한다.
		//    > 아바타정보 테이블에 달란트 정보 업데이트한다.

		/**
		 * C. 포인트로그가 변동 상황이 발생이 되었을 경우 실행
		 */
		// 1. 연속 판정: 학생이 오늘 퀘스트를 완료하면 USER_QUEST_CONTINUITY
		//    테이블을 확인합니다. 없으면 insert 있으면 update
		// 2. LAST_SUCCESS_DE[마지막 성공 날짜 (연속 여부 판정용)]가 어제라면
		//    CURRENT_STREAK[현재 연속 수행 일수]를 +1 시킵니다.
		// 3. LAST_SUCCESS_DE가 오늘이라면: 이미 완료한 것이므로 변화 없음.
		// 4. LAST_SUCCESS_DE가 오늘,어제가 아니면: CURRENT_STREAK[현재 연속 수행 일수]를 1로 리셋합니다.
		// 5. 보상 지급: 갱신된 CURRENT_STREAK 값이 QUEST_CONTINUITY_RULES의 CONTINUITY_DAYS와 일치하는 시점에
		//    포인트와 경험치를 지급하고 로그(EXP_LOGS, POINT_LOGS)를 남깁니다.
		// 참고. 최대 기록: CURRENT_STREAK가 MAX_STREAK보다 커지면 최대 기록도 함께 업데이트하여 나중에 '최장 연속 달성 상장' 등을 줄 때 활용할 수 있습니다.

		/**
		 * D. 포인트로그, 경험치 로그에 데이터 수정 발생 시 토스트 매시지 저장
		 */

		// 4. 학생순번, 퀘스트순번을 활용하여 포인트, 경험치 수치를 찾아해당 학생에게 포인트와 경험치를 넣어준다.
		//  > 경험치 수치를 업데이트하는 것은 레벨업과 관련 되어있음... 레벨업되면 달란트도 지급함..;;
		//  > 보너스 경험치도 있음 이것 또한 고려해야함.
		// 5. 마지막으로 해당 학생에게 토스트DB에 데이너 넣는다.

		//rewardMapper.updateReward(reward);
	}
}
