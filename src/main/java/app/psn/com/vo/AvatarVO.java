package app.psn.com.vo;

/**
 * 아바타 정보를 담는 불변 데이터 객체
 */
public record AvatarVO(
	int mberSn,       // 회원_일련번호
	String ncnm,      // 회원_닉네임
	int level,        // 레벨
	int exp,          // 경험치(스냅샷)
	int point,        // 달란트(스냅샷)
	String occpCode   // 직업/퀘스트 유형 코드
) {
	// record는 기본적으로 모든 필드에 대한 getter, toString, equals, hashCode를 제공합니다.
}