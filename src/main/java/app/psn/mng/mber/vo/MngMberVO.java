package app.psn.mng.mber.vo;

/**
 * 퀘스트승인 정보
 */
public record MngMberVO( // 회원정보
                         Integer mberSn,            // 회원_일련번호
                         String mberId,             // 회원_아이디
                         String mberNm,             // 회원_이름
                         String sexdstn,            // 성별
                         String occpCode,           // 직업_코드
                         String occpNm,             // 직업_명
                         String grade,              // 학년
                         String cla,                // 반
                         String guildNm,            // 길드_명
                         Integer level,             // 레벨
                         Integer exp,               // 누적경험치
                         Integer currExp,           // 현제 레벨의 경험치
                         Integer maxExp,            // 현제 레벨업하기 위한 경험치
                         Integer point,             // 달란트
                         String creatDate           // 가입_일자
) {
}