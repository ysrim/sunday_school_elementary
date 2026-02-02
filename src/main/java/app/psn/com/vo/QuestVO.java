package app.psn.com.vo;

/**
 * Quest 정보를 담는 불변 데이터 객체 (Record)
 */
public record QuestVO( // 퀘스트 정보
					   Integer questSn,     // 퀘스트_일련번호
					   String title,        // 퀘스트_제목
					   String description,  // 퀘스트_설명
					   Integer rewardExp,   // 경험치
					   Integer rewardPoint, // 달란트
					   String questType,    // 퀘스트_유형
					   String emoji,        // 이모지
					   String targetGrade   // 대상
) {
}