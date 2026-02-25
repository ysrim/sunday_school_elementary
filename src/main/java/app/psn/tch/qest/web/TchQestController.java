package app.psn.tch.qest.web;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.base.annotation.tch.TchMenuInfo;
import com.base.enumm.com.MberGrdEnum;
import com.base.enumm.com.ViewPathEnum;
import com.base.enumm.tch.TchNaviEnum;
import com.base.utl.ResUtil;
import com.base.vo.ResponseBody;

import app.psn.tch.qest.service.TchQestService;
import app.psn.tch.qest.vo.ReqQestProcVO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/tch")
@TchMenuInfo(navi = TchNaviEnum.TCH_QEST, role = MberGrdEnum.TCH)
public class TchQestController {

	private final TchQestService tchQestService;

	/**
	 * 퀘스트 페이지
	 */
	@RequestMapping("/qest.pg")
	public String questPg() {

		return ViewPathEnum.TCH.to("/qest/tchQest");

	}

	/**
	 * 퀘스트 리스트
	 */
	@RequestMapping("/qest/reqQestList.ax")
	public ResponseEntity<ResponseBody<Object>> reqAtndAx(@RequestParam(value = "reqMonth", defaultValue = "") String reqMonth) {

		if ("".equals(reqMonth)) {
			ResUtil.resFail("월을 입력해주세요! ❌");
		}

		return ResUtil.resSucc("성공! ✅", tchQestService.sltReqQestList(reqMonth));

	}

	/**
	 * 퀘스트 승인/반려
	 */
	@RequestMapping("/qest/chkQest.ax")
	public ResponseEntity<ResponseBody<Object>> atndChkAx(@Valid ReqQestProcVO reqQestProcVO) {

		tchQestService.questProc(reqQestProcVO);

		return ResUtil.resSucc("APPROVED".equals(reqQestProcVO.getStatus()) ? "처리가 완료되었습니다! ✅" : "반려 처리되었습니다. ❌");

	}

}