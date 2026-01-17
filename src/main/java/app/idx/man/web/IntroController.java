package app.idx.man.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import app.idx.man.service.IntroService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/idx")
public class IntroController {

	private final IntroService introService;

	@RequestMapping("/intro.pg")
	public String introPage(Model model) {
		model.addAttribute("stuMberCnt", introService.stuMberCnt());
		return "/app/idx/intro";
	}

}