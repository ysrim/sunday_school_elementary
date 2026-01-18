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

		// sql가져오기
		// 1. 길드원 숫자
		// 2. 길드목원 목록 레벨 높은 사람 > 이름 순 으로 3명 까지
		// 3. 공지사항
		// 4. 오늘의 말씀

		return "/app/psn/stu/page/home";
	}

}