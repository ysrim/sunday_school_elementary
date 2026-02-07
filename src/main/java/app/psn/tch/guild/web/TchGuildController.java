package app.psn.tch.guild.web;

import app.psn.std.home.service.StdHomeService;
import com.base.annotation.std.StdMenuInfo;
import com.base.annotation.tch.TchMenuInfo;
import com.base.enumm.com.MberGrdEnum;
import com.base.enumm.com.ViewPathEnum;
import com.base.enumm.std.StdNaviEnum;
import com.base.enumm.tch.TchNaviEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/tch")
public class TchGuildController {

    /**
     * 길드 정보
     */
    @TchMenuInfo(navi = TchNaviEnum.TCH_GILD, role = MberGrdEnum.TCH)
    @RequestMapping("/gild.pg")
    public String guildPg() {

        return ViewPathEnum.TCH.to("/gild/tchGild");

    }

}