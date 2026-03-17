package app.psn.std.login.web;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.base.enumm.com.ViewPathEnum;
import com.base.utl.CommonUtil;
import com.base.utl.ResUtil;
import com.base.utl.SessionUtil;
import com.base.vo.ResponseBody;

import app.psn.com.vo.LoginVO;
import app.psn.std.login.service.StdLoginService;
import app.psn.std.login.vo.StdSessionVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
	public String loginPg(@CookieValue(name = "refreshToken", required = false, defaultValue = "") String refreshToken //
		, @CookieValue(name = "autoLogin", required = false, defaultValue = "") String autoLogin) {

		// 자동로그인이 활성화 && 리프레쉬 토큰 유무 && 리프레쉬토큰으로 로그인 시도 성공시 => 홈으로 리다이렉션
		if (!"".equals(autoLogin) && !"".equals(refreshToken) && stdLoginService.refreshTokenValid(refreshToken)) {
			return "redirect:/std/home.pg";
		}

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
	public String logOutAx(HttpServletResponse response, HttpSession session) {

		// 로그아웃하면 자동로그인 해제를 해야한다. 토큰삭제, 쿠키값 자동로그인 삭제
		// 1. 리프레쉬 토큰 삭제
		stdLoginService.regRefreshToken(SessionUtil.getStdMberInfo().mberId(), "");
		// 2. 쿠키값 자동로그인 해제
		CommonUtil.deleteCookie(response, "autoLogin");

		session.invalidate(); // 세션 삭제

		return "redirect:/std/idx/intro.pg";

	}

}