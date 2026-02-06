package app.psn.std.attend.web;

import app.psn.std.attend.service.AttendanceService;

import com.base.annotation.MenuInfo;
import com.base.enumm.com.MberGrdEnum;
import com.base.enumm.com.ViewPathEnum;
import com.base.enumm.std.NaviEnum;
import com.base.utl.ResUtil;
import com.base.utl.StringUtil;

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
public class AttendanceController {

    private final AttendanceService attendanceService;

    /**
     * 주일 출석페이지
     */
    @MenuInfo(navi = NaviEnum.STD_ATND, role = MberGrdEnum.STD)
    @RequestMapping("/atnd.pg")
    public String attendancePg(Model model) {

        model.addAttribute("attendanceList", attendanceService.sltAttendanceList());

        model.addAttribute("attenanceCount", attendanceService.sltAttendanceContinueCount());

        return ViewPathEnum.STD.to("/atnd/atnd");

    }

    /**
     * 출석 요청
     */
    @MenuInfo(role = MberGrdEnum.STD)
    @RequestMapping("/atnd.ax")
    public ResponseEntity attendanceAx() {

        if (StringUtil.isTodaySunday()) {
            attendanceService.attendanceDo();
            return ResUtil.resSucc();
        } else {
            return ResUtil.resFail("주일만 출석 가능합니다.");
        }

    }

}