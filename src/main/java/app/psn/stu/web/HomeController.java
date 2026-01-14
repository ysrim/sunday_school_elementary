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
		return "/std/page/home";
	}

}