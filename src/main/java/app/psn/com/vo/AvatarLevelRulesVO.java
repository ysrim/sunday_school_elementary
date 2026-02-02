package app.psn.com.vo;

/**
 * 아바타 레벨 및 보상 규칙 정보
 */
public record AvatarLevelRulesVO( // 아바타 레벨 및 보상 규칙 정보
								  Integer level,        // 아바타_레벨
								  Integer requiredExp,  // 레벨 도달에 필요한 최초 누적 경험치
								  String rankName,      // 해당 레벨의 호칭
								  Integer rewardPoint   // 레벨업시 지급할 달란트
) {
	// record는 기본적으로 모든 필드에 대한 getter, toString, equals, hashCode를 제공합니다.
}