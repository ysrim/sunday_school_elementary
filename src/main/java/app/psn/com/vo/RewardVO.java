package app.psn.com.vo;

public record RewardVO( //
						int mberSn,       // 지급할 회원
						String rewardType,      // 자산 유형 ('POINT', 'EXP')
						int amount,   // 획득량
						String changeType, // 획득경로('QUEST', 'ADMIN_MANUAL', 'EVENT')
						int referenceSn, // 관련ID(ex. 퀘스트로그테이블의 logSn)
						String description // 상세내용
) {
}