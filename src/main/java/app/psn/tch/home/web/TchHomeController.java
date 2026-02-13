package app.psn.tch.home.web;

import app.psn.tch.home.service.TchHomeService;
import com.base.annotation.tch.TchMenuInfo;
import com.base.enumm.com.MberGrdEnum;
import com.base.enumm.com.ViewPathEnum;
import com.base.enumm.tch.TchNaviEnum;
import com.base.utl.CommonUtil;
import com.base.utl.ResUtil;
import com.base.vo.ResponseBody;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/tch")
@TchMenuInfo(navi = TchNaviEnum.TCH_HOME, role = MberGrdEnum.TCH)
public class TchHomeController {

	private final TchHomeService tchHomeService;

	/**
	 * 홈 대쉬보드 페이지
	 */
	@RequestMapping("/home.pg")
	public String homePg(Model model) {

		// 대쉬보드
		model.addAttribute("dashboard", tchHomeService.dashboard());

		// 길드 메시지
		model.addAttribute("gildMsg", tchHomeService.gildMsg());

		return ViewPathEnum.TCH.to("/home/tchHome");

	}

	/**
	 * 오늘의 길드 메시지 작성
	 */
	@RequestMapping("/home/saveGildMsg.ax")
	public ResponseEntity<ResponseBody<Object>> saveGildMsgAx(@RequestParam(value = "slogan", defaultValue = "") String slogan) {

		if ("".equals(slogan) || slogan.getBytes().length < 400) {
			ResUtil.resFail("메시지를 입력해주세요! ❌");
		}

		tchHomeService.regGildMsg(CommonUtil.xssSanitize(slogan));

		return ResUtil.resSucc("등록되었습니다! ✅");

	}

	/**
	 * 길드 포스트 삭제
	 */
	@RequestMapping("/home/delGildPost.ax")
	public ResponseEntity<ResponseBody<Object>> delGildPostAx(@RequestParam(value = "postSn", defaultValue = "") String postSn) {

		if ("".equals(postSn)) {
			ResUtil.resFail("필수값이 부족합니다! ❌");
		}

		tchHomeService.delGildPost(Integer.parseInt(postSn));

		return ResUtil.resSucc("선택한 포스트가 삭제되었습니다! ✅");

	}

	/**
	 * 길드 포스트 추가
	 */
	@RequestMapping("/home/regGildPost.ax")
	public ResponseEntity<ResponseBody<Object>> regGildPost(@RequestParam(value = "content", defaultValue = "") String content) {

		if ("".equals(content)) {
			ResUtil.resFail("필수값이 부족합니다! ❌");
		}

		tchHomeService.regGildPost(CommonUtil.xssSanitize(content));

		return ResUtil.resSucc("이야기가 공유되었습니다! 🚀");

	}

	/**
	 * 길드 포스트 추가
	 */
	@RequestMapping("/home/getGildPost.ax")
	public ResponseEntity<ResponseBody<Object>> regGildPost() {

		return ResUtil.resSucc("포스트가 조회되었습니다! ✅", tchHomeService.getTchGildPost());

	}

}