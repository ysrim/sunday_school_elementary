package app.psn.stu.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.base.annotation.MenuInfo;
import com.base.enumm.MberGrdEnum;
import com.base.enumm.NaviEnum;
import com.base.utl.SessionUtil;

import app.idx.lgn.vo.SessionVO;
import app.psn.com.mapper.CacheMapper;
import app.psn.com.service.CacheService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/std")
public class HomeController {

	private final CacheService cacheService;

	@MenuInfo(navi = NaviEnum.STD_HOME, role = MberGrdEnum.STD)
	@RequestMapping("/home.pg")
	public String homePg(HttpSession session, Model model) {

		int mberSn = SessionUtil.getMberInfo().getMberSn();
		model.addAttribute("mberPoint", cacheService.sltPont(mberSn)); // 달란트
		model.addAttribute("mberLv", cacheService.sltLv(mberSn)); // 레벨
		model.addAttribute("mberExp", cacheService.sltExp(mberSn)); // 경험치
		model.addAttribute("todayBibleVerse", cacheService.sltTodayBibleVerse()); // 오늘의 말씀

		// sql가져오기
		// 1. 길드원 숫자 (접속자 // 전체숫자)
		// 2. 길드명, 길드마크
		// 3. 일일퀘스트 완료하면 오늘도 퀘스트 뜸
		// 4. 길드원 목록 나타나기(접속자 표시)
		// 5. 공지사항

		return "/app/psn/stu/page/home";

	}

}