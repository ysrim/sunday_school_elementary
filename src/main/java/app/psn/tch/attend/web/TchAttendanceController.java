package app.psn.tch.attend.web;

import app.psn.tch.attend.service.TchAttendanceService;
import com.base.annotation.tch.TchMenuInfo;
import com.base.enumm.com.MberGrdEnum;
import com.base.enumm.com.ViewPathEnum;
import com.base.enumm.tch.TchNaviEnum;
import com.base.utl.StringUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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

        // 현재원 가져오기

        model.addAttribute("sltMonthList", tchAttendanceService.sltMonthList());
        model.addAttribute("sltWeekList", tchAttendanceService.sltWeekList(StringUtil.getCurrentYearMonth()));


        // 주

        return ViewPathEnum.TCH.to("/atnd/tchAtnd");

    }

}