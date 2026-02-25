package app.psn.tch.gild.web;

import app.psn.tch.gild.service.TchGildService;
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
@TchMenuInfo(navi = TchNaviEnum.TCH_GILD, role = MberGrdEnum.TCH)
public class TchGildController {

	private final TchGildService tchGildService;

	/**
	 * 길드 정보
	 */
	@RequestMapping("/gild.pg")
	public String guildPg(Model model) {

		model.addAttribute("tchGildMemberList", tchGildService.sltTchGuildMberList());

		return ViewPathEnum.TCH.to("/gild/tchGild");

	}

}