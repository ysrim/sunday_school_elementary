package app.err.web;

import com.base.enumm.com.ViewPathEnum;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
public class ErrorController {

	/**
	 * error page sending
	 */
	@GetMapping({"/404.pg", "/400.pg", "/500.pg"})
	public String error404() {

		return ViewPathEnum.DEF.to("/err/errorPage");

	}

}