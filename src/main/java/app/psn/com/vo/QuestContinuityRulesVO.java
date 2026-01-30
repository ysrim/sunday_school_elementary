package app.psn.com.vo;

/**
 * 연속 수행 보너스 지급 규칙 정의
 */
public record QuestContinuityRulesVO(
	int questSn,         // 퀘스트_일련번호
	int continuityDays,  // 연속목표_수행일
	int bonusExp,        // 보너스_경험치
	int BonusPoint,      // 보너스_달란트
	String description   // 설명
) {
	// record는 기본적으로 모든 필드에 대한 getter, toString, equals, hashCode를 제공합니다.
}