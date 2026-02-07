package app.psn.mng.rewd.web;

import app.psn.com.service.CacheService;
import app.psn.com.service.ToastMsgService;
import app.psn.std.home.service.StdHomeService;
import app.psn.std.quest.service.StdQuestService;
import com.base.annotation.mng.MngMenuInfo;
import com.base.annotation.std.StdMenuInfo;
import com.base.enumm.com.MberGrdEnum;
import com.base.enumm.com.ViewPathEnum;
import com.base.enumm.mng.MngNaviEnum;
import com.base.enumm.std.StdNaviEnum;
import com.base.utl.ResUtil;
import com.base.utl.SessionUtil;
import com.base.utl.StringUtil;
import com.base.vo.BodyResVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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