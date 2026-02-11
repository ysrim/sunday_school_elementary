package app.psn.tch.home.web;

import app.psn.tch.home.service.TchHomeService;

import com.base.annotation.tch.TchMenuInfo;
import com.base.enumm.com.MberGrdEnum;
import com.base.enumm.com.ViewPathEnum;
import com.base.enumm.tch.TchNaviEnum;
import com.base.utl.ResUtil;
import com.base.utl.StringUtil;
import com.base.vo.BodyResVO;

import app.psn.tch.quest.vo.ReqQuestProcVO;
import jakarta.validation.Valid;
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
public class TchHomeController {

	private final TchHomeService tchHomeService;

	/**
	 * 홈 대쉬보드 페이지
	 */
	@TchMenuInfo(navi = TchNaviEnum.TCH_HOME, role = MberGrdEnum.TCH)
	@RequestMapping("/home.pg")
	public String homePg(Model model) {

		// 대쉬보드
		model.addAttribute("dashboard", tchHomeService.dashboard());

		// 길드 메시지
		model.addAttribute("gildMsg", tchHomeService.gildMsg());

		// 길드 포스트
		model.addAttribute("gildPost", tchHomeService.getTchGildPost());

		return ViewPathEnum.TCH.to("/home/tchHome");

	}

	/**
	 * 오늘의 길드 메시지 작성
	 */
	@TchMenuInfo(navi = TchNaviEnum.TCH_QEST, role = MberGrdEnum.TCH)
	@RequestMapping("/home/saveGildMsg.ax")
	public ResponseEntity<BodyResVO<Object>> saveGildMsgAx(@RequestParam(value = "slogan", defaultValue = "") String slogan) {

		if ("".equals(slogan) || slogan.getBytes().length < 400) {
			ResUtil.resFail("메시지를 입력해주세요!");
		}

		tchHomeService.saveGildMsgAx(StringUtil.xssSanitize(slogan));

		return ResUtil.resSucc("등록되었습니다! ✅");

	}

}