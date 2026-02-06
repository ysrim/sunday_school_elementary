package app.psn.std.join.web;

import com.base.enumm.com.ViewPathEnum;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.base.utl.ResUtil;

import app.psn.std.join.service.JoinService;
import app.psn.std.join.vo.JoinMemberVO;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/std/idx")
public class JoinController {

    private final JoinService joinService;

    private final PasswordEncoder passwordEncoder;

    /**
     * 회원 가입 페이지
     */
    @RequestMapping(path = "/join.pg")
    public String joinPage(@ModelAttribute("joinFm") JoinMemberVO joinMemberVO) {

        return ViewPathEnum.STD.to("/idx/join");

    }

    /**
     * 회원 가입 요청
     */
    @RequestMapping(path = "/join.ax")
    @ResponseBody
    public ResponseEntity joinAx(@Valid JoinMemberVO joinMemberVO) {

        joinMemberVO.setPwd(passwordEncoder.encode(joinMemberVO.getPwd()));

        joinService.joinMber(joinMemberVO);

        return ResUtil.resSucc("가입을 축하합니다! " + joinMemberVO.getNcnm() + " 용사님!");

    }

    /**
     * 아이디 중복 체크 요청
     */
    @RequestMapping(path = "/idDupleChk.ax")
    @ResponseBody
    public ResponseEntity idDupleChkAx(@RequestParam(name = "mberId", defaultValue = "") String mberId) {

        if ("".equals(mberId)) {
            return ResUtil.resValid("잘못된 형식의 아이디입니다.");
        }

        return joinService.idDupleChk(mberId) ? ResUtil.resSucc("사용 가능한 아이디입니다.") : ResUtil.resFail("이미 사용 중인 아이디입니다.");

    }

}