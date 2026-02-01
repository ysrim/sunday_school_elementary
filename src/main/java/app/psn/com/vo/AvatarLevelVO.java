package app.psn.com.vo;

/**
 * 아바타 정보를 담는 불변 데이터 객체
 */
public record AvatarLevelVO( // 아바타 레벨 정보
							 int currentLevel,         // 현재 레벨
							 int currentRequiredExp,   // 현제 레벨이 달성하기 위한 필요 누적 경험치
							 int nextLevel,            // 다음 레벨
							 int nextRequiredExp,      // 다음 레벨이 달성하기 위한 필요 누적 경험치
							 int levelUpRequiredExp,   // 다음 레벨을 달성하기 위한 경험치
							 int totalExp,             // 회원이 획득한 누적 경험치
							 int currentExp,           // 현재 경험치
							 int expNeeded,            // 렙업하기 위한 필요 경험치
							 int progressPercent       // 현재 레벨, 다음레벨 경험치 획득 퍼센트
) {
	// record는 기본적으로 모든 필드에 대한 getter, toString, equals, hashCode를 제공합니다.
}