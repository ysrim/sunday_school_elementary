package app.psn.com.service.impl;

import java.security.Security;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.psn.com.service.PushNotificationService;
import app.psn.com.vo.SubscriptionVO;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.martijndwars.webpush.Notification;
import nl.martijndwars.webpush.PushService;

@Slf4j
@Service("pushNotificationService")
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PushNotificationServiceImpl implements PushNotificationService {

	private PushService pushService;

	@PostConstruct
	public void init() throws Exception {
		// 1. 보안 제공자 등록 (최초 1회)
		if (Security.getProvider("BC") == null) {
			Security.addProvider(new BouncyCastleProvider());
		}
		// 2. VAPID 키를 사용하여 PushService 초기화

		pushService = new PushService("BJ22p17Q8L5AvttIJWCToZ_OzXqWKXusFRDlPckdAAi4gn9I9_W5imWs0sAln4XWGXCw5EfOO8FNpb4rcJqBJVE", "wBBYgkF7a1oM244RJOvlySRFBPPjVNRAolrr466jWGM", "푸쉬테스트");
	}

	public void sendPush(SubscriptionVO sub, String title, String message, String targetUrl) {
		try {
			// 3. 전송할 데이터 JSON 생성 (sw.js가 받을 형식)
			String payload = String.format(
				"{\"title\":\"%s\", \"body\":\"%s\", \"url\":\"%s\"}",
				title, message, targetUrl
			);

			// 4. Notification 객체 생성 (DB 정보 활용)
			Notification notification = new Notification(
				sub.endpoint(),
				sub.keys().p256dh(),
				sub.keys().auth(),
				payload
			);

			// 5. 발송!
			pushService.send(notification);
			System.out.println("푸시 발송 성공!");

		} catch (Exception e) {
			System.err.println("푸시 발송 실패: " + e.getMessage());
			// 여기서 410 Gone 에러가 나면 해당 구독 정보는 DB에서 삭제해야 합니다 (사용자가 알림 취소함)
		}
	}

}