package app.psn.stu.web;

import app.idx.reg.service.JoinService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@Slf4j
@Controller
@RequestMapping("/std")
public class MainController {

	@Resource(name = "joinService")
	private JoinService joinService;

	@RequestMapping("/home.pg")
	public String dashboard(Model model) {

		model.addAttribute("items", Arrays.asList("데이터1", "데이터2"));

		// "th/" 패턴이므로 Thymeleaf가 처리
		// 이 파일을 열면 layout:decorate 설정 때문에 defaultLayout과 합쳐져서 렌더링됨

		return "/std/page/home";
	}

}