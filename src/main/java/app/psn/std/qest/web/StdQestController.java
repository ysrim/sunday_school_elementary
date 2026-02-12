package app.psn.std.qest.web;

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

import app.psn.std.qest.service.StdQestService;
import app.psn.std.qest.vo.StdQestPendingVO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/std")
public class StdQestController {

	private final StdQestService stdQestService;

	/**
	 * 퀘스트 페이지
	 */
	@StdMenuInfo(navi = StdNaviEnum.STD_QEST, role = MberGrdEnum.STD)
	@RequestMapping("/qest.pg")
	public String qestPg(Model model) {

		model.addAttribute("isAttnd", StringUtil.isTodaySunday()); // 주일 여부

		model.addAttribute("qestList", stdQestService.sltQestList());

		return ViewPathEnum.STD.to("/qest/stdQest");

	}

	/**
	 * 퀘스트 수행 요청
	 */
	@StdMenuInfo(role = MberGrdEnum.STD)
	@RequestMapping("/qest/qest.ax")
	public ResponseEntity<BodyResVO<Object>> qestAx(@Valid StdQestPendingVO questVO) {

		stdQestService.qestDo(questVO);

		return ResUtil.resSucc();

	}

}