package app.psn.stu.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.base.annotation.MenuInfo;
import com.base.enumm.MberGrdEnum;
import com.base.enumm.NaviEnum;
import com.base.utl.SessionUtil;

import app.psn.com.service.CacheService;
import app.psn.stu.service.HomeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/std")
public class HomeController {

	private final CacheService cacheService;

	private final HomeService homeService;

	@MenuInfo(navi = NaviEnum.STD_HOME, role = MberGrdEnum.STD)
	@RequestMapping("/home.pg")
	public String homePg(Model model) {

		// cache
		int mberSn = SessionUtil.getMberInfo().getMberSn();
		model.addAttribute("mberPoint", cacheService.sltPont(mberSn)); // 달란트
		model.addAttribute("mberLv", cacheService.sltLv(mberSn)); // 레벨
		model.addAttribute("mberExp", cacheService.sltExp(mberSn)); // 경험치
		model.addAttribute("todayBibleVerse", cacheService.sltTodayBibleVerse()); // 오늘의 말씀

		// sql
		int guildSn = SessionUtil.getMberInfo().getGuildSn();
		model.addAttribute("guildMberCnt", homeService.sltGuildMberList(guildSn).size()); // 길드 숫자
		model.addAttribute("guildMberAccessList", homeService.sltGuildMberAccessList(guildSn)); // 길드접속자 목록
		model.addAttribute("guildInfo", homeService.sltGuildInfo(guildSn)); // 길드정보
		// 1. 길드원 목록 나타나기(접속자 표시) > (접속자 // 전체숫자)
		// 2. 길드명, 길드마크
		// 3. 일일퀘스트 완료하면 오늘도 퀘스트 뜸
		// 4.
		// 5. 공지사항

		return "/app/psn/stu/page/home/home";

	}

	@MenuInfo(navi = NaviEnum.STD_HOME_SUB, role = MberGrdEnum.STD)
	@RequestMapping("/home/noticeCont.pg")
	public String noticeContPg(Model model) {
		// 공지사항 컨텐츠 to-be
		return "/app/psn/stu/page/home/noticeCont";

	}

}