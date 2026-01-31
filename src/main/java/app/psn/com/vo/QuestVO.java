package app.psn.com.vo;

/**
 * Quest 정보를 담는 불변 데이터 객체 (Record)
 */
public record QuestVO(
	int questSn,        // 퀘스트_일련번호
	String title,       // 퀘스트_제목
	String description, // 퀘스트_설명
	int rewardExp,      // 경험치
	int rewardPoint,    // 달란트
	String questType,   // 퀘스트_유형
	String emoji,       // 이모지
	String targetGrade  // 대상
) {
	// 필요한 경우 컴팩트 생성자를 통해 유효성 검증 로직을 넣을 수 있습니다.
}