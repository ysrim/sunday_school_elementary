package app.psn.mng.rewd.vo;

/**
 * 학생 정보
 */
public record MngStdVO( // 학생정보
                        String mberSn,     // 회원_일련번호
                        String mberId,     // 회원_아이디
                        String mberNm,     // 회원_이름
                        String sexdstn,    // 성별
                        String ncnm,       // 닉네임
                        String occpCode,   // 직업_코드
                        String occpNm,     // 직업_명
                        Integer exp,       // 경험치
                        Integer level,     // level
                        Integer point,     // 달란트
                        String guildNm,    // 길드_명
                        String grade,      // 학년
                        String cla         // 반
) {
}