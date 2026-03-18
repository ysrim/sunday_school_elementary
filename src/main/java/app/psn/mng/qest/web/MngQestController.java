package app.psn.mng.qest.web;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.base.annotation.mng.MngMenuInfo;
import com.base.enumm.com.MberGrdEnum;
import com.base.enumm.com.ViewPathEnum;
import com.base.enumm.mng.MngNaviEnum;
import com.base.utl.ResUtil;
import com.base.vo.ResponseBody;

import app.psn.mng.qest.service.MngQestService;
import app.psn.mng.qest.vo.MngReqQuestProcVO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/mng")
public class MngQestController {

	private final MngQestService mngQestService;

	@MngMenuInfo(navi = MngNaviEnum.MNG_QEST_MNG, role = MberGrdEnum.MNG)
	@RequestMapping("/qestInfo.pg")
	public String qestInfoPg() {

		return ViewPathEnum.MNG.to("/qest/mngQestInfo");

	}

	/**
	 * 퀘스트 페이지
	 */
	@MngMenuInfo(navi = MngNaviEnum.MNG_QEST, role = MberGrdEnum.MNG)
	@RequestMapping("/qest.pg")
	public String qestPg() {

		return ViewPathEnum.MNG.to("/qest/mngQest");

	}

	/**
	 * 퀘스트 수행 목록
	 */
	@MngMenuInfo(navi = MngNaviEnum.MNG_QEST, role = MberGrdEnum.MNG)
	@RequestMapping("/qest/getQestLogList.ax")
	public ResponseEntity<ResponseBody<Object>> getQestLogListAx() {

		return ResUtil.resSucc(mngQestService.getQestLogList());

	}

	/**
	 * 퀘스트 승인/반려
	 */
	@MngMenuInfo(navi = MngNaviEnum.MNG_QEST, role = MberGrdEnum.MNG)
	@RequestMapping("/qest/qestProc.ax")
	public ResponseEntity<ResponseBody<Object>> atndChkAx(@RequestBody @Valid MngReqQuestProcVO mngReqQuestProcVO) {

		mngQestService.questProc(mngReqQuestProcVO);

		return ResUtil.resSucc("APPROVED".equals(mngReqQuestProcVO.getStatus()) ? "처리가 완료되었습니다! ✅" : "반려 처리되었습니다. ❌");

	}

}