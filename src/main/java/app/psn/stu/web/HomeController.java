package app.psn.stu.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.base.annotation.MenuInfo;
import com.base.enumm.MberGrdEnum;
import com.base.enumm.NaviEnum;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/std")
public class HomeController {

	@MenuInfo(navi = NaviEnum.STD_HOME, role = MberGrdEnum.STD)
	@RequestMapping("/home.pg")
	public String homePg(Model model) {

		//길드, 달란트
		// 아바타명

		// 오늘의 말씀

		// 소속한 길드명, 길드레벨, 길드원

		// 공지사항

		return "/app/psn/stu/page/home";
	}

}