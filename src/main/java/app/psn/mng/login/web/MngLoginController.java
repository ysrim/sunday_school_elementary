package app.psn.mng.login.web;

import app.psn.mng.login.service.MngLoginService;
import app.psn.mng.login.vo.MngLoginVO;
import app.psn.mng.login.vo.MngSessionVO;
import com.base.enumm.com.ViewPathEnum;
import com.base.utl.ResUtil;
import com.base.vo.ResponseBody;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/mng/idx")
public class MngLoginController {

	private final MngLoginService loginService;

	/**
	 * 로그인 페이지
	 */
	@RequestMapping(path = "/login.pg")
	public String loginPg() {

		return ViewPathEnum.MNG.to("/idx/mngLogin");

	}

	/**
	 * 로그인 요청
	 */
	@PostMapping(path = "/login.ax")
	public ResponseEntity<ResponseBody<Object>> loginAx(@RequestBody @Valid MngLoginVO loginVO) {

		MngSessionVO sessionVO = loginService.loginAx(loginVO);

		return ResUtil.resSucc(sessionVO.ncnm() + "용사님 어서오세요.");

	}

	/**
	 * 로그아웃
	 */
	@RequestMapping(path = "/logOut.pg")
	public String logOutAx(HttpSession session) {

		session.invalidate();

		return "redirect:/mng/idx/login.pg";

	}

}