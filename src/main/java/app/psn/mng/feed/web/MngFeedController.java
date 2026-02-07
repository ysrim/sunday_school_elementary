package app.psn.mng.feed.web;

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
public class MngFeedController {

    @MngMenuInfo(navi = MngNaviEnum.MNG_FEED, role = MberGrdEnum.MNG)
    @RequestMapping("/feed.pg")
    public String feedPg() {

        return ViewPathEnum.MNG.to("/feed/mngFeed");

    }

}