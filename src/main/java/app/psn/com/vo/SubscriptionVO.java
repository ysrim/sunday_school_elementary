package app.psn.com.vo;

public record SubscriptionVO(String endpoint, Long expirationTime, Keys keys) {
	// 내부 클래스도 record로 정의 가능합니다.
	public record Keys(String p256dh, String auth) {
	}
}
