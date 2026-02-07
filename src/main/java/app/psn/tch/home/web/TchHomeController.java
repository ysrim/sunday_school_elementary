package app.psn.tch.home.web;

import com.base.annotation.tch.TchMenuInfo;
import com.base.enumm.com.MberGrdEnum;
import com.base.enumm.com.ViewPathEnum;
import com.base.enumm.tch.TchNaviEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/tch")
public class TchHomeController {

    /**
     * 홈 대쉬보드 페이지
     */
    @TchMenuInfo(navi = TchNaviEnum.TCH_HOME, role = MberGrdEnum.TCH)
    @RequestMapping("/home.pg")
    public String homePg() {

        return ViewPathEnum.TCH.to("/home/tchHome");

    }

}