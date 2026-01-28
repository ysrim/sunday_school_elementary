package app.psn.com.vo;

public record RewardVO(
	String mberSn,    // 지급할 회원
	String questSn    // 완료한 퀘스트 순번
) {
}
