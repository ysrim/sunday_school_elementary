package app.psn.mng.rewd.vo;

/**
 * 리워드 지급 이력
 */
public record MngRewdHistVO( // 학생정보
                             Integer logSn,       // 일련번호
                             String creatDate,    // 지급시간
                             String mberSn,       // 회원_일련번호
                             String mberNm,       // 회원_이름
                             String rewardType,   // 지급유형
                             Integer amount,      // 지급량
                             String referenceSn,  // 지급한 관리자 Sn
                             String mngNm,        // 지급한 관리자 명
                             String description,  // 지급사유
                             String grade,        // 학년
                             String cla           // 반
) {
}