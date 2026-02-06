package app.psn.std.guild.web;

import app.psn.std.home.service.HomeService;
import com.base.annotation.MenuInfo;
import com.base.enumm.com.MberGrdEnum;
import com.base.enumm.com.ViewPathEnum;
import com.base.enumm.std.NaviEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/std")
public class GuildController {

    private final HomeService homeService;

    /**
     * 길드 정보
     */
    @MenuInfo(navi = NaviEnum.STD_GILD, role = MberGrdEnum.STD)
    @RequestMapping("/gild.pg")
    public String guildPg(Model model) {

        model.addAttribute("guildMberCnt", homeService.sltGuildMberList().size()); // 길드 숫자
        model.addAttribute("guildMberAccessList", homeService.sltGuildMberList()); // 길드접속자 목록
        model.addAttribute("guildInfo", homeService.sltGuildInfo()); // 길드정보

        return ViewPathEnum.STD.to("/gild/gild");

    }

}