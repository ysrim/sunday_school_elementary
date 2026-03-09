package app.psn.mng.home.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.base.annotation.mng.MngMenuInfo;
import com.base.enumm.com.MberGrdEnum;
import com.base.enumm.com.ViewPathEnum;
import com.base.enumm.mng.MngNaviEnum;

import app.psn.mng.home.service.MngHomeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/mng")
@MngMenuInfo(navi = MngNaviEnum.MNG_HOME, role = MberGrdEnum.MNG)
public class MngHomeController {

	private final MngHomeService mngHomeService;

	@RequestMapping("/home.pg")
	public String homePg(Model model) {

		model.addAttribute("getAtndCnt", mngHomeService.getAtndCnt());
		model.addAttribute("getQestCnt", mngHomeService.getQestCnt());
		model.addAttribute("getWeekPoint", mngHomeService.getWeekPoint());
		model.addAttribute("getSumPoint", mngHomeService.getSumPoint());
		model.addAttribute("getAtndList", mngHomeService.getAtndList());
		model.addAttribute("getAvatarLevel", mngHomeService.getAvatarLevel());
		model.addAttribute("getTimeVisit", mngHomeService.getTimeVisit());
		model.addAttribute("getGradeClaAtndCnt", mngHomeService.getGradeClaAtndCnt());
		model.addAttribute("getExcellentMber", mngHomeService.getExcellentMber());

		return ViewPathEnum.MNG.to("/home/mngHome");

	}

}