package app.psn.std.login.web;

import app.psn.com.vo.LoginVO;
import app.psn.std.login.service.StdLoginService;
import app.psn.std.login.vo.StdSessionVO;
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
	public ResponseEntity<ResponseBody<Object>> loginAx(@RequestBody @Valid LoginVO loginVO) {

		StdSessionVO stdSessionVO = stdLoginService.loginAx(loginVO);

		return ResUtil.resSucc(stdSessionVO.ncnm() + "용사님 어서오세요! ✅");

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