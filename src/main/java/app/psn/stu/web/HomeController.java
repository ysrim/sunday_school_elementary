package app.psn.stu.web;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.base.annotation.MenuInfo;
import com.base.enumm.MberGrdEnum;
import com.base.enumm.NaviEnum;
import com.base.utl.ResUtil;
import com.base.utl.SessionUtil;
import com.base.utl.StringUtil;

import app.psn.com.service.CacheService;
import app.psn.com.service.ToastMsgService;
import app.psn.stu.service.HomeService;
import app.psn.stu.service.QuestService;
import app.psn.stu.vo.QuestPendingVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/std")
public class HomeController {

	private final CacheService cacheService;

	private final HomeService homeService;

	private final QuestService questService;

	private final ToastMsgService toastMsgService;

	@MenuInfo(navi = NaviEnum.STD_HOME, role = MberGrdEnum.STD)
	@RequestMapping("/home.pg")
	public String homePg(Model model) {

		Integer mberSn = SessionUtil.getMberInfo().getMberSn();
		Integer guildSn = SessionUtil.getMberInfo().getGuildSn();

		// cache
		model.addAttribute("mberPoint", cacheService.sltPont(mberSn)); // 달란트
		model.addAttribute("mberLv", cacheService.sltLevel(mberSn)); // 레벨
		model.addAttribute("mberExp", cacheService.sltExp(mberSn)); // 경험치
		model.addAttribute("todayBibleVerse", cacheService.sltTodayBibleVerse()); // 오늘의 말씀

		// sql
		model.addAttribute("guildMberCnt", homeService.sltGuildMberList(guildSn).size()); // 길드 숫자
		model.addAttribute("guildMberAccessList", homeService.sltGuildMberAccessList(guildSn)); // 길드접속자 목록
		model.addAttribute("guildInfo", homeService.sltGuildInfo(guildSn)); // 길드정보
		model.addAttribute("toastMsgList", toastMsgService.sltToastMsgList(mberSn)); // 토스트 메시지
		model.addAttribute("qestCmpleteYn", questService.questCompleteChk(StringUtil.setQuestPendingVO(4)) ? "Y" : "N"); // 맑씀읽기 퀘스트 여부

		// 미구현 기능: 공지사항, 길드마크

		return "/app/psn/stu/page/home/home";

	}

	@MenuInfo(navi = NaviEnum.STD_HOME_1, role = MberGrdEnum.STD)
	@RequestMapping("/home/noticeCont.pg")
	public String noticeContPg(Model model) {
		// TODO 공지사항 컨텐츠 구현
		return "/app/psn/stu/page/home/noticeCont";

	}

	@MenuInfo(role = MberGrdEnum.STD)
	@RequestMapping("/home/removeToast.ax")
	public ResponseEntity removeToastAx(@RequestParam(name = "toastSn", defaultValue = "0") String toastSn) {
		if ("0".equals(toastSn)) {
			return ResUtil.resFail("변수 부족");
		}
		toastMsgService.removeToast(Integer.parseInt(toastSn));
		return ResUtil.resSucc();
	}

	@MenuInfo(role = MberGrdEnum.STD)
	@RequestMapping("/home/wordsAmen.ax")
	public ResponseEntity wordsAmenAx() {
		return homeService.wordsAmenDo() ? ResUtil.resSucc() : ResUtil.resFail("이미 수행");
	}

}