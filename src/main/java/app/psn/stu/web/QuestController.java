package app.psn.stu.web;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.base.annotation.MenuInfo;
import com.base.enumm.MberGrdEnum;
import com.base.enumm.NaviEnum;
import com.base.utl.ResUtil;
import com.base.utl.SessionUtil;
import com.base.utl.StringUtil;

import app.psn.stu.service.OptionService;
import app.psn.stu.service.QuestService;
import app.psn.stu.vo.QuestVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/std")
public class QuestController {

	private final QuestService questService;

	@MenuInfo(navi = NaviEnum.STD_QEST, role = MberGrdEnum.STD)
	@RequestMapping("/qest.pg")
	public String questPg(Model model) {
		model.addAttribute("isAttendance", StringUtil.isTodaySunday()); // 주일 여부
		model.addAttribute("questList", questService.sltQuestList());
		return "/app/psn/stu/page/qest/qest";
	}

	@MenuInfo(role = MberGrdEnum.STD)
	@RequestMapping("/qest/qest.ax")
	public ResponseEntity qestAx(QuestVO questVO) {
		if ("".equals(questVO.getQuestSn())) {
			return ResUtil.resValid("퀘스트 정보가 없습니다.");
		} else {
			questService.questDo(questVO);
			return ResUtil.resSucc();
		}
	}

}