package app.psn.tch.attend.web;

import com.base.annotation.tch.TchMenuInfo;
import com.base.enumm.com.MberGrdEnum;
import com.base.enumm.com.ViewPathEnum;
import com.base.enumm.tch.TchNaviEnum;
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

    /**
     * 주일 출석페이지
     */
    @TchMenuInfo(navi = TchNaviEnum.TCH_ATND, role = MberGrdEnum.TCH)
    @RequestMapping("/atnd.pg")
    public String attendancePg(Model model) {

        return ViewPathEnum.TCH.to("/atnd/tchAtnd");

    }

}