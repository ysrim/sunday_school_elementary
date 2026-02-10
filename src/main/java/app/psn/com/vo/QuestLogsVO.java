package app.psn.com.vo;

import com.base.enumm.com.QuestLogStatusEnum;

/**
 * Quest 로그
 */
public record QuestLogsVO( // 퀘스트 로그 정보
                           Integer logSn,             // 퀘스트_수행_일련번호
                           String reqDate,            // 퀘스트_수행_일자
                           Integer mberSn,            // 회원_일련번호
                           Integer questSn,           // 퀘스트_일련번호
                           QuestLogStatusEnum status, // 퀘스트_수행_상태 enum('PENDING', 'APPROVED', 'REJECTED')
                           String proofText,          // 요청_내용
                           String proofImageUrl,      // 요청_이미지_URL
                           String adminComment,       // 관리자_커맨트
                           String processedBy,        // 승인자_일련번호
                           String processedDe,        // 승인_날짜
                           String creatDe             // 생성_날짜
) {
}