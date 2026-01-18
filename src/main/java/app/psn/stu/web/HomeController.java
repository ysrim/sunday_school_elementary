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

		// 캐쉬데이터
		// 1. 달란트
		// 2. 레벨
		// 3. 경험치

		// sql가져오기
		// 1. 길드원 숫자
		// 2. 길드목원 목록 레벨 높은 사람 > 이름 순 으로 3명 까지
		// 3. 공지사항
		// 4. 오늘의 말씀

		return "/app/psn/stu/page/home";
	}

}