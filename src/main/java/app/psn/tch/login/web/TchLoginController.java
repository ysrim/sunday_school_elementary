package app.psn.tch.login.web;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.base.enumm.com.ViewPathEnum;
import com.base.utl.ResUtil;
import com.base.vo.BodyResVO;

import app.psn.tch.login.service.TchLoginService;
import app.psn.tch.login.vo.TchLoginVO;
import app.psn.tch.login.vo.TchSessionVO;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/tch/idx")
public class TchLoginController {

    private final TchLoginService loginService;

    /**
     * 로그인 페이지
     */
    @RequestMapping(path = "/login.pg")
    public String loginPg() {

        return ViewPathEnum.TCH.to("/idx/tchLogin");

    }

    /**
     * 로그인 요청
     */
    @PostMapping(path = "/login.ax")
    public ResponseEntity<BodyResVO<Object>> loginAx(@RequestBody @Valid TchLoginVO loginVO) {

        TchSessionVO sessionVO = loginService.loginAx(loginVO);

        return ResUtil.resSucc(sessionVO.ncnm() + "용사님 어서오세요.");

    }

    /**
     * 로그아웃
     */
    @RequestMapping(path = "/logOut.pg")
    public String logOutAx(HttpSession session) {

        session.invalidate();

        return "redirect:/tch/idx/login.pg";

    }

}