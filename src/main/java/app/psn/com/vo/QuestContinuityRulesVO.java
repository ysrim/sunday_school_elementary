package app.psn.com.vo;

/**
 * 연속 수행 보너스 지급 규칙 정의
 */
public record QuestContinuityRulesVO( // 연속 수행 보너스
									  Integer questSn,         // 퀘스트_일련번호
									  Integer continuityDays,  // 연속목표_수행일
									  Integer bonusExp,        // 보너스_경험치
									  Integer BonusPoint,      // 보너스_달란트
									  String description   // 설명
) {
}