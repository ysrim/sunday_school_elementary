package app.psn.com.vo;

import com.base.enumm.com.RewardGetPathEnum;
import com.base.enumm.com.RewardTypeEnum;
import com.base.vo.QuestCompleteEvent;

public record RewardVO( //
						Integer mberSn,      // 지급할 회원
						String rewardType,   // 자산 유형 ('POINT', 'EXP')
						Integer amount,      // 획득량
						String changeType,   // 획득경로('QUEST', 'ADMIN_MANUAL', 'EVENT', 'CONTINUITY', 'LEVEL')
						Integer referenceSn, // 관련ID(ex. 퀘스트로그테이블의 logSn)
						String description   // 상세내용
) {
	public static RewardVO ofQuestPoint(QuestCompleteEvent event, QuestVO quest) {
		return new RewardVO(event.mberSn(), RewardTypeEnum.POINT.name(), //
				quest.rewardPoint(), RewardGetPathEnum.QUEST.name(), //
				event.logSn(), quest.description() + " 퀘스트 보상");
	}

	public static RewardVO ofQuestExp(QuestCompleteEvent event, QuestVO quest) {
		return new RewardVO(event.mberSn(), RewardTypeEnum.EXP.name(), //
				quest.rewardExp(), RewardGetPathEnum.QUEST.name(), //
				event.logSn(), quest.description() + " 퀘스트 보상");
	}

	public static RewardVO ofContinuityPoint(QuestCompleteEvent event, QuestContinuityRulesVO questContinuityRules) {
		return new RewardVO(event.mberSn(), RewardTypeEnum.POINT.name(), //
				questContinuityRules.BonusPoint(), RewardGetPathEnum.QUEST.name(), //
				-1, questContinuityRules.description() + " 연퀘 보상");
	}

	public static RewardVO ofContinuityExp(QuestCompleteEvent event, QuestContinuityRulesVO questContinuityRules) {
		return new RewardVO(event.mberSn(), RewardTypeEnum.EXP.name(), //
				questContinuityRules.bonusExp(), RewardGetPathEnum.QUEST.name(), //
				-2, questContinuityRules.description() + "연퀘 보상");
	}

	public static RewardVO ofLevelUpPoint(Integer mberSn, Integer Point) {
		return new RewardVO(mberSn, RewardTypeEnum.POINT.name(), //
				Point, RewardGetPathEnum.LEVEL_UP.name(), //
				0, "Level Up 축하!");
	}

}