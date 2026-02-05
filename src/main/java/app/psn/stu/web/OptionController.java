package app.psn.stu.web;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.base.annotation.MenuInfo;
import com.base.enumm.MberGrdEnum;
import com.base.enumm.NaviEnum;
import com.base.utl.ResUtil;
import com.base.utl.SessionUtil;

import app.psn.stu.service.HomeService;
import app.psn.stu.service.OptionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/std")
public class OptionController {

    private final HomeService homeService;
    private final OptionService optionService;

    // 마이페이지 - 메인
    @MenuInfo(navi = NaviEnum.STD_OPTS, role = MberGrdEnum.STD)
    @RequestMapping("/opts.pg")
    public String guildPg(Model model) {
        model.addAttribute("guildMberCnt", homeService.sltGuildMberList().size()); // 길드맴버 숫자
        return "/app/psn/stu/page/opts/opts";
    }

    // 마이페이지 - QR코드
    @MenuInfo(navi = NaviEnum.STD_OPTS_1, role = MberGrdEnum.STD)
    @RequestMapping("/opts/qrCode.pg")
    public String qrCodePg(Model model) {
        model.addAttribute("qrStr", optionService.QrCodeStr());
        return "/app/psn/stu/page/opts/qrCode";
    }

    // 마이페이지 - 패스워드 변경
    @MenuInfo(navi = NaviEnum.STD_OPTS_2, role = MberGrdEnum.STD)
    @RequestMapping("/opts/pwChg.pg")
    public String pwChgPg() {
        return "/app/psn/stu/page/opts/pwChg";
    }

    // 마이페이지 - 패스워드 변경 - 요청
    @MenuInfo(navi = NaviEnum.STD_OPTS_2, role = MberGrdEnum.STD)
    @RequestMapping("/opts/pwChg.ax")
    public ResponseEntity pwChgAx(@RequestParam(value = "currentPw", defaultValue = "") String currentPw // 기존 패스워드
            , @RequestParam(value = "newPw", defaultValue = "") String newPw // 기존 패스워드
    ) {
        if ("".equals(newPw) || "".equals(currentPw)) {
            ResUtil.resFail("변수가 부족합니다.");
        }
        optionService.pwChg(currentPw, newPw);
        return ResUtil.resSucc();
    }

    // 마이페이지 - 포인트 내역
    @MenuInfo(navi = NaviEnum.STD_OPTS_3, role = MberGrdEnum.STD)
    @RequestMapping("/opts/pointHistory.pg")
    public String pointHistoryPg(Model model) {
        model.addAttribute("guildMberCnt", optionService.sltPointHistory()); // 길드맴버 숫자
        return "/app/psn/stu/page/opts/pointHistory";
    }

}