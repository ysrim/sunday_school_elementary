package app.psn.std.intro.web;

import app.psn.std.intro.service.StdIntroService;
import com.base.enumm.com.ViewPathEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/std/idx")
public class StdIntroController {

	private final StdIntroService stdIntroService;

	/**
	 * 인트로 페이지
	 */
	@RequestMapping("/intro.pg")
	public String introPage(Model model) {

		model.addAttribute("stdMberCnt", stdIntroService.stdMberCnt());

		return ViewPathEnum.STD.to("/idx/intro");

	}

}