package app.psn.std.qest.web;

import app.psn.std.qest.service.StdQestService;
import app.psn.std.qest.vo.StdQestPendingVO;
import com.base.annotation.std.StdMenuInfo;
import com.base.enumm.com.MberGrdEnum;
import com.base.enumm.com.ViewPathEnum;
import com.base.enumm.std.StdNaviEnum;
import com.base.utl.CommonUtil;
import com.base.utl.ResUtil;
import com.base.vo.ResponseBody;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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

		model.addAttribute("isAttnd", CommonUtil.isTodaySunday()); // 주일 여부

		model.addAttribute("qestList", stdQestService.sltQestList());

		return ViewPathEnum.STD.to("/qest/stdQest");

	}

	/**
	 * 퀘스트 수행 요청
	 */
	@StdMenuInfo(role = MberGrdEnum.STD)
	@RequestMapping("/qest/qest.ax")
	public ResponseEntity<ResponseBody<Object>> qestAx(@Valid StdQestPendingVO questVO) {

		stdQestService.qestDo(questVO);

		return ResUtil.resSucc();

	}

}