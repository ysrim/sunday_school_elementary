package app.psn.mng.rewd.web;

import com.base.annotation.mng.MngMenuInfo;
import com.base.enumm.com.MberGrdEnum;
import com.base.enumm.com.ViewPathEnum;
import com.base.enumm.mng.MngNaviEnum;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/mng")
public class MngRewardController {

    @MngMenuInfo(navi = MngNaviEnum.MNG_REWD, role = MberGrdEnum.MNG)
    @RequestMapping("/rewd.pg")
    public String rewdPg() {

        return ViewPathEnum.MNG.to("/rewd/mngRewd");

    }

}