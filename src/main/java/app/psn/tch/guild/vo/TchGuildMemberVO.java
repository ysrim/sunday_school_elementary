package app.psn.tch.guild.vo;

/**
 * 길드원 목록 정보 VO
 */
public record TchGuildMemberVO( //
								String mberSn,    // 회원_일련번호
								String mberId,    // 회원_아이디
								String mberNm,    // 회원_이름
								String sexdstn,   // 성별
								String ncnm,      // 닉네임
								String occpCode,  // 직업_코드
								String occpNm,    // 직업_명
								Integer level,    // 캐릭터_레벨
								Integer point     // 포인트
) {
}