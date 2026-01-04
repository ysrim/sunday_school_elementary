package app.intro.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IntroController {

	@RequestMapping("/intro.do")
	public String intro() {
		// 방문자 카운트 증가
		return "/pages/app/intro";
	}

}