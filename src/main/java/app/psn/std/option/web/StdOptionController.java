package app.psn.std.option.web;

import app.psn.std.home.service.StdHomeService;
import app.psn.std.option.service.StdOptionService;

import com.base.annotation.std.StdMenuInfo;
import com.base.enumm.com.MberGrdEnum;
import com.base.enumm.com.ViewPathEnum;
import com.base.enumm.std.StdNaviEnum;
import com.base.utl.ResUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/std")
public class StdOptionController {

    private final StdHomeService homeService;

    private final StdOptionService stdOptionService;

    /**
     * 마이페이지 - 메인
     */
    @StdMenuInfo(navi = StdNaviEnum.STD_OPTS, role = MberGrdEnum.STD)
    @RequestMapping("/opts.pg")
    public String guildPg(Model model) {

        model.addAttribute("guildMberCnt", homeService.sltGuildMberList().size()); // 길드맴버 숫자

        return ViewPathEnum.STD.to("/opts/opts");

    }

    /**
     * 마이페이지 - QR코드
     */
    @StdMenuInfo(navi = StdNaviEnum.STD_OPTS_1, role = MberGrdEnum.STD)
    @RequestMapping("/opts/qrCode.pg")
    public String qrCodePg(Model model) {

        model.addAttribute("qrStr", stdOptionService.QrCodeStr());

        return ViewPathEnum.STD.to("/opts/qrCode");

    }

    /**
     * 마이페이지 - 패스워드 변경
     */
    @StdMenuInfo(navi = StdNaviEnum.STD_OPTS_2, role = MberGrdEnum.STD)
    @RequestMapping("/opts/pwChg.pg")
    public String pwChgPg() {

        return ViewPathEnum.STD.to("/opts/pwChg");

    }

    /**
     * 마이페이지 - 패스워드 변경 - 요청
     */
    @StdMenuInfo(navi = StdNaviEnum.STD_OPTS_2, role = MberGrdEnum.STD)
    @RequestMapping("/opts/pwChg.ax")
    public ResponseEntity pwChgAx(@RequestParam(value = "currentPw", defaultValue = "") String currentPw // 기존 패스워드
            , @RequestParam(value = "newPw", defaultValue = "") String newPw // 기존 패스워드
    ) {

        if ("".equals(newPw) || "".equals(currentPw)) {
            ResUtil.resFail("변수가 부족합니다.");
        }

        stdOptionService.pwChg(currentPw, newPw);

        return ResUtil.resSucc();

    }

    /**
     * 마이페이지 - 포인트 내역
     */
    @StdMenuInfo(navi = StdNaviEnum.STD_OPTS_3, role = MberGrdEnum.STD)
    @RequestMapping("/opts/pointHistory.pg")
    public String pointHistoryPg(Model model) {

        model.addAttribute("weekStatics", stdOptionService.sltWeekStatics());

        model.addAttribute("pointHistory", stdOptionService.sltRewardHistory());

        return ViewPathEnum.STD.to("/opts/pointHistory");

    }

}