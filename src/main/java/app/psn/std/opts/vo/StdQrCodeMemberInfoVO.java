package app.psn.std.opts.vo;

/**
 * QR코드 맴버정보
 */
public record StdQrCodeMemberInfoVO( //
									 String mberNm,     // 회원_이름
									 String gradeCode,  // 등급_코드
									 String ncnm,       // 닉네임
									 String occpCode,   // 직업_코드
									 Integer exp,       // 경험치
									 Integer level,     // 캐릭터_레벨
									 String guildNm,    // 길드_이름
									 String sexdstn,    // 성별
									 Integer grade,     // 등급 (기존 주석은 성별이었으나 필드명에 맞게 등급으로 이해)
									 Integer cla,       // 반/클래스 (기존 주석은 성별이었으나 필드명에 맞게 수정 가능)
									 String occpNm,     // 직업
									 Integer point      // 달란트
) {
}