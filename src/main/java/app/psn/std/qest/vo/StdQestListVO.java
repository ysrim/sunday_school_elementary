package app.psn.std.qest.vo;

/**
 * 퀘스트 목록
 */
public record StdQestListVO( //
                             Integer questSn,     // 퀘스트_일련번호
                             Integer mberSn,      // 회원_일련번호
                             String title,        // 퀘스트_제목
                             String description,  // 퀘스트_설명
                             Integer rewardExp,   // 경험치
                             Integer rewardPoint, // 달란트
                             String questType,    // 퀘스트_유형
                             String emoji,        // 이모지
                             String status,       // 요청_상태
                             String statusNm      // 요청_상태_명칭
) {
}