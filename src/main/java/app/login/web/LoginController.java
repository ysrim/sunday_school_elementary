package app.login.web;

import java.util.Arrays;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class LoginController {

	//	@Resource(name = "resService")
	//	private ResService resService;

	@RequestMapping(path = "login.pg")
	public String loginPage() {
		return "jsp/app/login";
	}

	@GetMapping("/dashboard.pg")
	public String dashboard(Model model) {
		model.addAttribute("items", Arrays.asList("데이터1", "데이터2"));

		// "th/" 패턴이므로 Thymeleaf가 처리
		// 실제 파일: /WEB-INF/templates/th/dashboard.html
		// 이 파일을 열면 layout:decorate 설정 때문에 defaultLayout과 합쳐져서 렌더링됨

		return "page/dashboard";
	}

}