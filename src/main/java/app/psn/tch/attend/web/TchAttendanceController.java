package app.psn.tch.attend.web;

import app.psn.tch.attend.service.TchAttendanceService;

import app.psn.tch.quest.vo.ReqQuestProcVO;
import com.base.annotation.tch.TchMenuInfo;
import com.base.enumm.com.MberGrdEnum;
import com.base.enumm.com.ViewPathEnum;
import com.base.enumm.tch.TchNaviEnum;
import com.base.utl.ResUtil;
import com.base.utl.StringUtil;
import com.base.vo.BodyResVO;

import app.psn.tch.login.vo.TchLoginVO;
import app.psn.tch.login.vo.TchSessionVO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/tch")
public class TchAttendanceController {

    private final TchAttendanceService tchAttendanceService;

    /**
     * 주일 출석페이지
     */
    @TchMenuInfo(navi = TchNaviEnum.TCH_ATND, role = MberGrdEnum.TCH)
    @RequestMapping("/atnd.pg")
    public String attendancePg(Model model) {

        return ViewPathEnum.TCH.to("/atnd/tchAtnd");

    }

    @TchMenuInfo(navi = TchNaviEnum.TCH_ATND, role = MberGrdEnum.TCH)
    @RequestMapping("/atnd/reqAtnd.ax")
    public ResponseEntity<BodyResVO<Object>> reqAtndAx(@RequestParam(value = "reqDate", defaultValue = "") String reqDate) {

        if ("".equals(reqDate)) {
            ResUtil.resFail("날짜를 입력해주세요.");
        }

        return ResUtil.resSucc("성공", tchAttendanceService.sltReqAtndList(reqDate));

    }

    @TchMenuInfo(navi = TchNaviEnum.TCH_ATND, role = MberGrdEnum.TCH)
    @RequestMapping("/atnd/atndChk.ax")
    public ResponseEntity<BodyResVO<Object>> atndChkAx(@Valid ReqQuestProcVO reqQuestProcVO) {

        tchAttendanceService.atndChk(reqQuestProcVO);

        return ResUtil.resSucc("APPROVED".equals(reqQuestProcVO.getStatus()) ? "처리가 완료되었습니다! ✅" : "반려 처리되었습니다. ⚠\uFE0F");

    }

}