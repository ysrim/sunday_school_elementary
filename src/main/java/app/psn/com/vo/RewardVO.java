package app.psn.com.vo;

import com.base.enumm.com.RewardGetPathEnum;
import com.base.enumm.com.RewardTypeEnum;
import com.base.vo.QuestCompleteEvent;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RewardVO( //

                        @NotNull(message = "회원 정보(mberSn)는 필수입니다.")
                        Integer mberSn,      // 지급할 회원

                        @NotBlank(message = "자산 유형은 필수입니다.")
                        @Pattern(regexp = "^(POINT|EXP)$", message = "유효하지 않은 자산 유형입니다.")
                        String rewardType,   // 자산 유형 ('POINT', 'EXP')

                        @NotNull(message = "수량은 필수입니다.")
                        Integer amount,      // 획득량

                        @NotBlank(message = "획득 경로(changeType)는 필수입니다.")
                        @Pattern(regexp = "^(QUEST|ADMIN_MANUAL|EVENT|CONTINUITY|LEVEL)$", message = "유효하지 않은 자산 유형입니다.")
                        String changeType,   // 획득경로('QUEST', 'ADMIN_MANUAL', 'EVENT', 'CONTINUITY', 'LEVEL')

                        Integer referenceSn, // 관련ID(ex. 퀘스트로그테이블의 logSn)

                        @Size(max = 200, message = "상세 내용은 200자 이내로 입력해주세요.")
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
	/*
	public static RewardVO ofMngManualPoint(QuestCompleteEvent event, QuestVO quest) {
		return new RewardVO(event.mberSn(), RewardTypeEnum.POINT.name(), //
			quest.rewardPoint(), RewardGetPathEnum.QUEST.name(), //
			event.logSn(), quest.description() + " 퀘스트 보상");
	}

	public static RewardVO ofMngManualExp(QuestCompleteEvent event, QuestVO quest) {
		return new RewardVO(event.mberSn(), RewardTypeEnum.EXP.name(), //
			quest.rewardExp(), RewardGetPathEnum.QUEST.name(), //
			event.logSn(), quest.description() + " 퀘스트 보상");
	}
	 */

}