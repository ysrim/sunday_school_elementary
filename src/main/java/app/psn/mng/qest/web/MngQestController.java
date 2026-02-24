package app.psn.mng.qest.web;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.base.annotation.mng.MngMenuInfo;
import com.base.annotation.std.StdMenuInfo;
import com.base.enumm.com.MberGrdEnum;
import com.base.enumm.com.ViewPathEnum;
import com.base.enumm.mng.MngNaviEnum;
import com.base.utl.CommonUtil;
import com.base.utl.ResUtil;
import com.base.vo.ResponseBody;

import app.psn.mng.qest.service.MngQestService;
import app.psn.mng.qest.vo.StdQestPendingVO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/mng")
@MngMenuInfo(navi = MngNaviEnum.MNG_QEST, role = MberGrdEnum.MNG)
public class MngQestController {

	private final MngQestService stdQestService;

	/**
	 * 퀘스트 페이지
	 */
	@RequestMapping("/qest.pg")
	public String qestPg(Model model) {

		//model.addAttribute("isAttnd", CommonUtil.isTodaySunday()); // 주일 여부

		//model.addAttribute("qestList", stdQestService.sltQestList());

		return ViewPathEnum.MNG.to("/qest/mngQest");

	}

	/**
	 * 퀘스트 수행 요청
	 */
	@RequestMapping("/qest/qest.ax")
	public ResponseEntity<ResponseBody<Object>> qestAx(@Valid StdQestPendingVO questVO) {

		stdQestService.qestDo(questVO);

		return ResUtil.resSucc();

	}

}