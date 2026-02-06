package app.psn.tch.login.vo;

/**
 * 세션 정보
 */
public record TchSessionVO( //
							Integer mberSn,    // 회원_일련번호
							String mberId,     // 회원_아이디
							String mberNm,     // 회원_이름
							String pwd,        // 패스워드
							String gradeCode,  // 등급_코드
							String ncnm,       // 닉네임
							String occpCode,   // 직업_코드
							String occpNm,     // 직업_이름
							Integer exp,       // 경험치
							Integer level,     // 캐릭터_레벨
							String guildNm,    // 길드_이름
							String sexdstn,    // 성별
							Integer guildSn,   // 길드_일련번호
							String grade,      // 학년
							String cla         // 반
) {
}