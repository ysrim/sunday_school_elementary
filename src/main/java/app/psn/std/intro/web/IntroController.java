package app.psn.std.intro.web;

import app.psn.std.intro.service.IntroService;
import com.base.enumm.com.ViewPathEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/std/idx")
public class IntroController {

    private final IntroService introService;

    /**
     * 인트로 페이지
     */
    @RequestMapping("/intro.pg")
    public String introPage(Model model) {

        model.addAttribute("stdMberCnt", introService.stdMberCnt());

        return ViewPathEnum.STD.to("/idx/intro");

    }

}