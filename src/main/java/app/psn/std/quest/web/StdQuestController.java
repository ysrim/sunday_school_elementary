package app.psn.std.quest.web;

import com.base.enumm.com.ViewPathEnum;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.base.annotation.std.StdMenuInfo;
import com.base.enumm.com.MberGrdEnum;
import com.base.enumm.std.StdNaviEnum;
import com.base.utl.ResUtil;
import com.base.utl.StringUtil;
import com.base.vo.BodyResVO;

import app.psn.std.quest.service.StdQuestService;
import app.psn.std.quest.vo.StdQuestPendingVO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/std")
public class StdQuestController {

	private final StdQuestService stdQuestService;

	/**
	 * 퀘스트 페이지
	 */
	@StdMenuInfo(navi = StdNaviEnum.STD_QEST, role = MberGrdEnum.STD)
	@RequestMapping("/qest.pg")
	public String questPg(Model model) {

		model.addAttribute("isAttendance", StringUtil.isTodaySunday()); // 주일 여부

		model.addAttribute("questList", stdQuestService.sltQuestList());

		return ViewPathEnum.STD.to("/qest/qest");

	}

	/**
	 * 퀘스트 수행 요청
	 */
	@StdMenuInfo(role = MberGrdEnum.STD)
	@RequestMapping("/qest/qest.ax")
	public ResponseEntity<BodyResVO<Object>> qestAx(@Valid StdQuestPendingVO questVO) {

		stdQuestService.questDo(questVO);

		return ResUtil.resSucc();

	}

}