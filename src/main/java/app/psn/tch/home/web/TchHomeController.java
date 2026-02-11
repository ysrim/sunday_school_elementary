package app.psn.tch.home.web;

import app.psn.tch.home.service.TchHomeService;
import com.base.annotation.tch.TchMenuInfo;
import com.base.enumm.com.MberGrdEnum;
import com.base.enumm.com.ViewPathEnum;
import com.base.enumm.tch.TchNaviEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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

		model.addAttribute("dashboard", tchHomeService.dashboard());

		return ViewPathEnum.TCH.to("/home/tchHome");

	}

}