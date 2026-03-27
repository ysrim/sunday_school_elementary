package app.psn.com.service;

import app.psn.com.vo.SubscriptionVO;

public interface PushNotificationService {

	/**
	 * 푸쉬메시지 발송
	 */
	public void sendPush(SubscriptionVO sub, String title, String message, String targetUrl);

}