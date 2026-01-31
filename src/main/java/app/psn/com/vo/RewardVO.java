package app.psn.com.vo;

import com.base.enumm.RewardGetPathEnum;
import com.base.enumm.RewardTypeEnum;
import com.base.vo.QuestCompleteEvent;

public record RewardVO( //
						int mberSn,          // 지급할 회원
						String rewardType,   // 자산 유형 ('POINT', 'EXP')
						int amount,          // 획득량
						String changeType,   // 획득경로('QUEST', 'ADMIN_MANUAL', 'EVENT', 'CONTINUITY', 'LEVEL')
						int referenceSn,     // 관련ID(ex. 퀘스트로그테이블의 logSn)
						String description   // 상세내용
) {
	public static RewardVO ofQuestPoint(QuestCompleteEvent event, QuestVO quest) {
		return new RewardVO(event.mberSn(), RewardTypeEnum.POINT.name(), //
			quest.rewardPoint(), RewardGetPathEnum.QUEST.name(), //
			event.logSn(), "퀘스트 완료 보상");
	}

	public static RewardVO ofQuestExp(QuestCompleteEvent event, QuestVO quest) {
		return new RewardVO(event.mberSn(), RewardTypeEnum.EXP.name(), //
			quest.rewardExp(), RewardGetPathEnum.QUEST.name(), //
			event.logSn(), "퀘스트 완료 보상");
	}

	public static RewardVO ofContinuityPoint(QuestCompleteEvent event, QuestContinuityRulesVO questContinuityRules) {
		return new RewardVO(event.mberSn(), RewardTypeEnum.POINT.name(), //
			questContinuityRules.BonusPoint(), RewardGetPathEnum.QUEST.name(), //
			event.logSn(), "연속 퀘스트 완료 보상");
	}

	public static RewardVO ofContinuityExp(QuestCompleteEvent event, QuestContinuityRulesVO questContinuityRules) {
		return new RewardVO(event.mberSn(), RewardTypeEnum.EXP.name(), //
			questContinuityRules.bonusExp(), RewardGetPathEnum.QUEST.name(), //
			event.logSn(), "연속 퀘스트 완료 보상");
	}

	public static RewardVO ofLevelUpPoint(int mberSn, int Point) {
		return new RewardVO(mberSn, RewardTypeEnum.POINT.name(), //
			Point, RewardGetPathEnum.LEVEL_UP.name(), //
			0, "레벨업 축하 달란트 지급");
	}

}