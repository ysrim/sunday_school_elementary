package app.psn.std.login.web;

import com.base.enumm.com.ViewPathEnum;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.base.utl.ResUtil;
import com.base.vo.BodyResVO;

import app.psn.std.login.service.StdLoginService;
import app.psn.com.vo.LoginVO;
import app.psn.std.login.vo.StdSessionVO;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/std/idx")
public class StdLoginController {

    private final StdLoginService stdLoginService;

    /**
     * 로그인 페이지
     */
    @RequestMapping(path = "/login.pg")
    public String loginPg() {

        return ViewPathEnum.STD.to("/idx/login");

    }

    /**
     * 로그인 요청
     */
    @PostMapping(path = "/login.ax")
    public ResponseEntity<BodyResVO<Object>> loginAx(@RequestBody @Valid LoginVO loginVO) {

        StdSessionVO stdSessionVO = stdLoginService.loginAx(loginVO);

        return ResUtil.resSucc(stdSessionVO.ncnm() + "용사님 어서오세요.");

    }

    /**
     * 로그아웃
     */
    @RequestMapping(path = "/logOut.pg")
    public String logOutAx(HttpSession session) {

        session.invalidate();

        return "redirect:/std/idx/intro.pg";

    }

}