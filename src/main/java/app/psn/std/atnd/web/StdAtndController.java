package app.psn.std.atnd.web;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.base.annotation.std.StdMenuInfo;
import com.base.enumm.com.MberGrdEnum;
import com.base.enumm.com.ViewPathEnum;
import com.base.enumm.std.StdNaviEnum;
import com.base.utl.ResUtil;
import com.base.utl.StringUtil;
import com.base.vo.BodyResVO;

import app.psn.std.atnd.service.StdAtndService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/std")
@StdMenuInfo(navi = StdNaviEnum.STD_ATND, role = MberGrdEnum.STD)
public class StdAtndController {

	private final StdAtndService stdAtndService;

	/**
	 * 주일 출석페이지
	 */
	@RequestMapping("/atnd.pg")
	public String atndPg(Model model) {

		model.addAttribute("attendanceList", stdAtndService.sltAtndList());

		model.addAttribute("attenanceCount", stdAtndService.sltCtnuAtndCnt());

		return ViewPathEnum.STD.to("/atnd/stdAtnd");

	}

	/**
	 * 출석 요청
	 */
	@RequestMapping("/atnd.ax")
	public ResponseEntity<BodyResVO<Object>> atndAx() {

		if (StringUtil.isTodaySunday()) {
			stdAtndService.atndDo();
			return ResUtil.resSucc();
		} else {
			return ResUtil.resFail("주일만 출석 가능합니다! ❌");
		}

	}

}