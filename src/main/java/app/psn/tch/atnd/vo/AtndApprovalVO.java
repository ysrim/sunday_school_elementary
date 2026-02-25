package app.psn.tch.atnd.vo;

/**
 * 출석정보 승인 정보
 */
public record AtndApprovalVO( //
							  Integer mberSn,       // 회원_일련번호 (ex. YYYY-MM-DD)
							  Integer questSn,      // 퀘스트_일련번호
							  Integer logSn,        // 퀘스트수행 로그 일련번호
							  String creatDate,     // 출석 요청 시간
							  String processedDe,   // 선생님 확인 시간
							  String mberNm,        // 회원_이름
							  String status         // 요청상태('PENDING'대기,'APPROVED'승인,'REJECTED'반려)
) {
}