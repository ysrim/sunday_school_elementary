package app.psn.tch.quest.web;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.base.annotation.tch.TchMenuInfo;
import com.base.enumm.com.MberGrdEnum;
import com.base.enumm.com.ViewPathEnum;
import com.base.enumm.tch.TchNaviEnum;
import com.base.utl.ResUtil;
import com.base.vo.BodyResVO;

import app.psn.tch.quest.service.TchQuestService;
import app.psn.tch.quest.vo.ReqQuestProcVO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/tch")
public class TchQuestController {

	private final TchQuestService tchQuestService;

	/**
	 * 퀘스트 페이지
	 */
	@TchMenuInfo(navi = TchNaviEnum.TCH_QEST, role = MberGrdEnum.TCH)
	@RequestMapping("/qest.pg")
	public String questPg() {

		return ViewPathEnum.TCH.to("/qest/tchQest");

	}

	/**
	 * 출석 요청 리스트
	 */
	@TchMenuInfo(navi = TchNaviEnum.TCH_QEST, role = MberGrdEnum.TCH)
	@RequestMapping("/qest/reqQestList.ax")
	public ResponseEntity<BodyResVO<Object>> reqAtndAx(@RequestParam(value = "reqMonth", defaultValue = "") String reqMonth) {

		if ("".equals(reqMonth)) {
			ResUtil.resFail("월을 입력해주세요.");
		}

		return ResUtil.resSucc("성공", tchQuestService.sltReqQestList(reqMonth));

	}

	/**
	 * 출석 승인/반려
	 */
	@TchMenuInfo(navi = TchNaviEnum.TCH_QEST, role = MberGrdEnum.TCH)
	@RequestMapping("/qest/chkQest.ax")
	public ResponseEntity<BodyResVO<Object>> atndChkAx(@Valid ReqQuestProcVO reqQuestProcVO) {

		tchQuestService.questProc(reqQuestProcVO);

		return ResUtil.resSucc("APPROVED".equals(reqQuestProcVO.getStatus()) ? "처리가 완료되었습니다! ✅" : "반려 처리되었습니다. ❌");

	}

}