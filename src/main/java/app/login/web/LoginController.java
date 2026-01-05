package app.login.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class LoginController {

	//	@Resource(name = "resService")
	//	private ResService resService;

	@RequestMapping(path = "/login.pg")
	public String loginPage() {
		return "/pages/app/login";
	}

}