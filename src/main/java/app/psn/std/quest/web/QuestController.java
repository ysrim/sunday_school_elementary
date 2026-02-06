package app.psn.std.quest.web;

import com.base.enumm.com.ViewPathEnum;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.base.annotation.MenuInfo;
import com.base.enumm.com.MberGrdEnum;
import com.base.enumm.std.NaviEnum;
import com.base.utl.ResUtil;
import com.base.utl.StringUtil;

import app.psn.std.quest.service.QuestService;
import app.psn.std.quest.vo.QuestPendingVO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/std")
public class QuestController {

    private final QuestService questService;

    /**
     * 퀘스트 페이지
     */
    @MenuInfo(navi = NaviEnum.STD_QEST, role = MberGrdEnum.STD)
    @RequestMapping("/qest.pg")
    public String questPg(Model model) {

        model.addAttribute("isAttendance", StringUtil.isTodaySunday()); // 주일 여부

        model.addAttribute("questList", questService.sltQuestList());

        return ViewPathEnum.STD.to("/qest/qest");

    }

    /**
     * 퀘스트 수행 요청
     *
     * @return
     */
    @MenuInfo(role = MberGrdEnum.STD)
    @RequestMapping("/qest/qest.ax")
    public ResponseEntity qestAx(@Valid QuestPendingVO questVO) {

        questService.questDo(questVO);

        return ResUtil.resSucc();

    }

}