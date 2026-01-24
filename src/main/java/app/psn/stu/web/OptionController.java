package app.psn.stu.web;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
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
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/std")
public class OptionController {

	private final OptionService optionService;

	@MenuInfo(navi = NaviEnum.STD_OPTS, role = MberGrdEnum.STD)
	@RequestMapping("/opts.pg")
	public String guildPg() {
		return "/app/psn/stu/page/opts/opts";
	}

	@MenuInfo(navi = NaviEnum.STD_OPTS_1, role = MberGrdEnum.STD)
	@RequestMapping("/opts/qrCode.pg")
	public String qrCodePg(Model model) {
		model.addAttribute("qrStr", optionService.QrCodeStr());
		return "/app/psn/stu/page/opts/qrCode";
	}

}