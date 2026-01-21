package app.psn.stu.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.base.annotation.MenuInfo;
import com.base.enumm.MberGrdEnum;
import com.base.enumm.NaviEnum;
import com.base.utl.SessionUtil;

import app.psn.stu.service.HomeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/std")
public class GuildController {

	private final HomeService homeService;

	@MenuInfo(navi = NaviEnum.STD_GILD, role = MberGrdEnum.STD)
	@RequestMapping("/gild.pg")
	public String guildPg(Model model) {

		int guildSn = SessionUtil.getMberInfo().getGuildSn();
		model.addAttribute("guildMberCnt", homeService.sltGuildMberList(guildSn).size()); // 길드 숫자
		model.addAttribute("guildMberAccessList", homeService.sltGuildMberList(guildSn)); // 길드접속자 목록
		model.addAttribute("guildInfo", homeService.sltGuildInfo(guildSn)); // 길드정보

		// 길드원 목록

		return "/app/psn/stu/page/gild/gild";
	}

}