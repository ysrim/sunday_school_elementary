package app.psn.com.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.base.enumm.com.ViewPathEnum;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class IntroController {

	@RequestMapping("/how/home.pg")
	public String guide() {

		return ViewPathEnum.HOW.to("/home");

	}

	@RequestMapping("/how/stdGuide.pg")
	public String stdGuide() {

		return ViewPathEnum.HOW.to("/stdGuide");

	}

	@RequestMapping("/how/mngGuide.pg")
	public String mngGuide() {

		return ViewPathEnum.HOW.to("/mngGuide");

	}

}