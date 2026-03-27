package app.psn.com.web;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.base.enumm.com.ViewPathEnum;
import com.base.utl.ResUtil;
import com.base.vo.ResponseBody;

import app.psn.com.service.PushNotificationService;
import app.psn.com.vo.SubscriptionVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class IntroController {

	private final PushNotificationService pushNotificationService;

	@RequestMapping("/how/home.pg")
	public String guide() {

		return ViewPathEnum.HOW.to("/home");

	}

	@RequestMapping("/how/stdGuide.pg")
	public String stdGuide() {

		return ViewPathEnum.HOW.to("/stdGuide");

	}

	@RequestMapping("/how/mngGuide.pg")
	public String mngGuide() {

		return ViewPathEnum.HOW.to("/mngGuide");

	}

	@PostMapping("/notifications/subscribe.ax")
	public ResponseEntity<ResponseBody<Object>> subscribe(@RequestBody SubscriptionVO subscriptionVO) {

		log.info("Endpoint: {}", subscriptionVO.endpoint());
		log.info("P256dh: {}", subscriptionVO.keys().p256dh());
		log.info("Auth: {}", subscriptionVO.keys().auth());

		return ResUtil.resSucc();

	}

	@GetMapping("/push.pg")
	@org.springframework.web.bind.annotation.ResponseBody
	public String testPush() {
		// 실제로는 DB에서 해당 사용자의 SubscriptionDto를 가져와야 합니다.
		// SubscriptionDto sub = myRepository.findByUserId("user123");

		//				sub.endpoint(),
		// 				sub.keys().p256dh(),
		// 				sub.keys().auth(),

		SubscriptionVO sub = new SubscriptionVO("endpoint", null, new SubscriptionVO.Keys("", ""));

		// 예시용 호출
		pushNotificationService.sendPush(sub, "Sunday School", "새로운 공지사항이 있습니다!", "/notice/123");

		return "발송 요청 완료";
	}

}