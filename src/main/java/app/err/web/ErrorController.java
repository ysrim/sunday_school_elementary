package app.err.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
public class ErrorController {

	// 404 에러 처리
	@GetMapping({"/404.pg", "/400.pg", "/500.pg"})
	public String error404() {
		return "/app/err/errorPage";
	}

}