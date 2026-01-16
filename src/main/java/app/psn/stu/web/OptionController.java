package app.psn.stu.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.base.annotation.MenuInfo;
import com.base.enumm.MberGrdEnum;
import com.base.enumm.NaviEnum;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/std")
public class OptionController {

	@MenuInfo(navi = NaviEnum.STD_OPTS, role = MberGrdEnum.STD)
	@RequestMapping("/opts.pg")
	public String guildPg(Model model) {
		return "/std/page/opts";
	}

}