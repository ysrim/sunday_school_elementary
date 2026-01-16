package app.idx.reg.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.base.utl.ResUtil;

import app.idx.reg.service.JoinService;
import app.idx.reg.vo.JoinMemberVO;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/idx")
public class JoinController {

	private final JoinService joinService;

	private final PasswordEncoder passwordEncoder;

	@RequestMapping(path = "/join.pg")
	public String joinPage(@ModelAttribute("joinFm") JoinMemberVO joinMemberVO) {
		return "jsp/app/join";
	}

	@RequestMapping(path = "/join.ax")
	@ResponseBody
	public ResponseEntity joinAx(@Valid JoinMemberVO joinMemberVO) {

		log.debug("joinMemberVO: {}", joinMemberVO);

		// 저장 시 암호화
		joinMemberVO.setPwd(passwordEncoder.encode(joinMemberVO.getPwd()));

		joinService.joinMber(joinMemberVO);

		return ResUtil.resSucc("가입을 축하합니다! " + joinMemberVO.getNcnm() + " 용사님!");

	}

	@RequestMapping(path = "/idDupleChk.ax")
	@ResponseBody
	public ResponseEntity idDupleChkAx(@RequestParam(name = "mberId", defaultValue = "") String mberId //
	) {

		log.debug("idDupleChkAx mberId: {}", mberId);
		if ("".equals(mberId)) {
			return ResUtil.resValid("잘못된 형식의 아이디입니다.");
		}

		return joinService.idDupleChk(mberId) ? ResUtil.resSucc("사용 가능한 아이디입니다.") : ResUtil.resFail("이미 사용 중인 아이디입니다.");

	}

}