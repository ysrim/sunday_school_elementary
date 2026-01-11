package app.psn.stu.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.base.annotation.MenuInfo;
import com.base.enumm.MberGrdEnum;

import app.idx.reg.service.JoinService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/std")
public class MainController {

	@Resource(name = "joinService")
	private JoinService joinService;

	@MenuInfo(seq = 1, role = MberGrdEnum.STD, name = "HOME")
	@RequestMapping("/home.pg")
	public String dashboard(Model model) {
		return "/std/page/home";
	}

}