package app.psn.com.service.impl;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.base.enumm.ToastTypeEnum;
import com.base.vo.ToastMsgEvent;

import app.psn.com.mapper.AvatarMapper;
import app.psn.com.service.AvatarService;
import app.psn.com.service.DomainService;
import app.psn.com.service.RewardService;
import app.psn.com.vo.AvatarLevelRulesVO;
import app.psn.com.vo.AvatarVO;
import app.psn.com.vo.RewardVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("avatarService")
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AvatarServiceImpl implements AvatarService {

	private final AvatarMapper avatarMapper;

	private final DomainService domainService;

	private final RewardService rewardService;

	private final ApplicationEventPublisher publisher;

	@Override
	@Transactional
	public void udtAvatarLevel(int mberSn) {

		// 1. 아바타 정보 조회 (현재 레벨, 누적 경험치)
		AvatarVO avatar = domainService.sltAvatar(mberSn);
		int currentLevel = avatar.level();

		// 2. 현재 경험치 기준 도달해야 할 '목표 레벨' 산출
		int targetLevel = avatarMapper.sltLevelRulesExp(avatar.exp());

		// 3. 레벨 변동이 없으면 즉시 종료
		if (targetLevel <= currentLevel) {
			return;
		}

		log.warn("사용자 {} 레벨업 감지: Lv.{} -> Lv.{}", mberSn, currentLevel, targetLevel);

		// 4. 레벨업 처리 루프 (한 번에 2업 이상 하는 경우 대비)
		for (int nextLevel = currentLevel + 1; nextLevel <= targetLevel; nextLevel++) {
			// 4-1. 리워드 달란트 정보 가져오기
			AvatarLevelRulesVO avatarLevelRules = domainService.sltAvatarLevelRules(nextLevel);
			// 4-2. 리워드 지급
			rewardService.insMberReward(RewardVO.ofLevelUpPoint(mberSn, avatarLevelRules.rewardPoint()));
		}

		// 5. 아바타 정보에 변경된 레벨 업데이트
		avatarMapper.udtAvatarLevel(mberSn, targetLevel);
		// (선택) 레벨업 축하 이벤트/알림 발행
		publisher.publishEvent(new ToastMsgEvent(mberSn, ToastTypeEnum.SUCCESS.toString(), "Level UP!", "축하합니다 용사님! " + currentLevel + "Lv에서 " + targetLevel + "Lv로 업했습니다."));

	}

}