package app.join.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class JoinController {

	//	@Resource(name = "resService")
	//	private ResService resService;

	@RequestMapping("/join.do")
	public String csDeploy() {
		return "/pages/app/join";
	}

}