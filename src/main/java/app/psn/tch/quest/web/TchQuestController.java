package app.psn.tch.quest.web;

import app.psn.tch.quest.service.TchQuestService;
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
public class TchQuestController {

    private final TchQuestService tchQuestService;

    /**
     * 퀘스트 페이지
     */
    @TchMenuInfo(navi = TchNaviEnum.TCH_QEST, role = MberGrdEnum.TCH)
    @RequestMapping("/qest.pg")
    public String questPg() {

        return ViewPathEnum.TCH.to("/qest/tchQest");

    }

}