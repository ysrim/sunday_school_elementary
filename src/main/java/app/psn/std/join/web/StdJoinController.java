package app.psn.std.join.web;

import app.psn.std.join.service.StdJoinService;
import app.psn.std.join.vo.StdJoinMemberVO;
import com.base.enumm.com.ViewPathEnum;
import com.base.utl.ResUtil;
import com.base.vo.ResponseBody;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/std/idx")
public class StdJoinController {

	private final StdJoinService stdJoinService;

	private final PasswordEncoder passwordEncoder;

	/**
	 * 회원 가입 페이지
	 */
	@RequestMapping(path = "/join.pg")
	public String joinPage(@ModelAttribute("joinFm") StdJoinMemberVO stdJoinMemberVO) {

		return ViewPathEnum.STD.to("/idx/join");

	}

	/**
	 * 회원 가입 요청
	 */
	@RequestMapping(path = "/join.ax")
	@org.springframework.web.bind.annotation.ResponseBody
	public ResponseEntity<ResponseBody<Object>> joinAx(@Valid StdJoinMemberVO stdJoinMemberVO) {

		stdJoinMemberVO.setPwd(passwordEncoder.encode(stdJoinMemberVO.getPwd()));

		stdJoinService.joinMber(stdJoinMemberVO);

		return ResUtil.resSucc("가입을 축하합니다! " + stdJoinMemberVO.getNcnm() + " 용사님! ✅");

	}

	/**
	 * 아이디 중복 체크 요청
	 */
	@RequestMapping(path = "/idDupleChk.ax")
	@org.springframework.web.bind.annotation.ResponseBody
	public ResponseEntity<ResponseBody<Object>> idDupleChkAx(@RequestParam(name = "mberId", defaultValue = "") String mberId) {

		if ("".equals(mberId)) {
			return ResUtil.resValid("잘못된 형식의 아이디입니다! ❌");
		}

		return stdJoinService.idDupleChk(mberId) ? ResUtil.resSucc("사용 가능한 아이디입니다! ✅") : ResUtil.resFail("이미 사용 중인 아이디입니다! ❌");

	}

}