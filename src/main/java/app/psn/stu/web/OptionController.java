package app.psn.stu.web;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.base.annotation.MenuInfo;
import com.base.enumm.MberGrdEnum;
import com.base.enumm.NaviEnum;
import com.base.utl.ResUtil;
import com.base.utl.SessionUtil;

import app.idx.reg.vo.JoinMemberVO;
import app.psn.stu.service.HomeService;
import app.psn.stu.service.OptionService;
import jakarta.validation.Valid;
import jakarta.websocket.Session;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/std")
public class OptionController {

	private final HomeService homeService;
	private final OptionService optionService;

	@MenuInfo(navi = NaviEnum.STD_OPTS, role = MberGrdEnum.STD)
	@RequestMapping("/opts.pg")
	public String guildPg(Model model) {
		model.addAttribute("guildMberCnt", homeService.sltGuildMberList(SessionUtil.getMberInfo().getGuildSn()).size()); // 길드맴버 숫자
		return "/app/psn/stu/page/opts/opts";
	}

	@MenuInfo(navi = NaviEnum.STD_OPTS_1, role = MberGrdEnum.STD)
	@RequestMapping("/opts/qrCode.pg")
	public String qrCodePg(Model model) {
		model.addAttribute("qrStr", optionService.QrCodeStr());
		return "/app/psn/stu/page/opts/qrCode";
	}

	@MenuInfo(navi = NaviEnum.STD_OPTS_2, role = MberGrdEnum.STD)
	@RequestMapping("/opts/pwChg.pg")
	public String pwChgPg() {
		return "/app/psn/stu/page/opts/pwChg";
	}

	@MenuInfo(navi = NaviEnum.STD_OPTS_2, role = MberGrdEnum.STD)
	@RequestMapping("/opts/pwChg.ax")
	public ResponseEntity pwChgAx(@RequestParam(value = "currentPw", defaultValue = "") String currentPw
		, @RequestParam(value = "newPw", defaultValue = "") String newPw, Model model) {
		if ("".equals(newPw) || "".equals(currentPw)) {
			ResUtil.resFail("변수가 부족합니다.");
		}
		optionService.pwChg(currentPw, newPw);
		//model.addAttribute("qrStr", optionService.QrCodeStr());
		return ResUtil.resSucc();
	}

}