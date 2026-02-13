package app.psn.std.option.vo;

/**
 * 적립 내역
 */
public record StdRewardHistoryVO( // 적립내역
								  String rewardType,    // 타입 (point, exp)
								  String changeType,    // 획득/쓰기 경로
								  String emoji,         // 이모지
								  String description,   // 사용내역 상세
								  String creatDe,       // 생성날짜
								  Integer amount,       // 금액(+,-)
								  Integer balanceAfter  // 남은 최종 금액
) {
}