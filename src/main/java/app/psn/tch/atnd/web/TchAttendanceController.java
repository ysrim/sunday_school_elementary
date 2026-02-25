package app.psn.tch.atnd.web;

import app.psn.tch.atnd.service.TchAttendanceService;
import app.psn.tch.qest.vo.ReqQestProcVO;
import com.base.annotation.tch.TchMenuInfo;
import com.base.enumm.com.MberGrdEnum;
import com.base.enumm.com.ViewPathEnum;
import com.base.enumm.tch.TchNaviEnum;
import com.base.utl.ResUtil;
import com.base.vo.ResponseBody;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/tch")
@TchMenuInfo(navi = TchNaviEnum.TCH_ATND, role = MberGrdEnum.TCH)
public class TchAttendanceController {

	private final TchAttendanceService tchAttendanceService;

	/**
	 * 주일 출석페이지
	 */
	@RequestMapping("/atnd.pg")
	public String attendancePg() {

		return ViewPathEnum.TCH.to("/atnd/tchAtnd");

	}

	/**
	 * 출석 요청 리스트
	 */
	@RequestMapping("/atnd/reqAtnd.ax")
	public ResponseEntity<ResponseBody<Object>> reqAtndAx(@RequestParam(value = "reqDate", defaultValue = "") String reqDate) {

		if ("".equals(reqDate)) {
			ResUtil.resFail("날짜를 입력해주세요! ❌");
		}

		return ResUtil.resSucc("성공! ✅", tchAttendanceService.sltReqAtndList(reqDate));

	}

	/**
	 * 출석 승인/반려
	 */
	@RequestMapping("/atnd/atndChk.ax")
	public ResponseEntity<ResponseBody<Object>> atndChkAx(@Valid ReqQestProcVO reqQestProcVO) {

		tchAttendanceService.atndChk(reqQestProcVO);

		return ResUtil.resSucc("APPROVED".equals(reqQestProcVO.getStatus()) ? "처리가 완료되었습니다! ✅" : "반려 처리되었습니다! ❌");

	}

}