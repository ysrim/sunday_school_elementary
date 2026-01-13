package app.idx.lgn.web;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.base.utl.ResUtil;

import app.idx.lgn.service.LoginService;
import app.idx.lgn.vo.LoginVO;
import app.idx.lgn.vo.SessionVO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/idx")
public class LoginController {

	@Resource(name = "loginService")
	private LoginService loginService;

	@RequestMapping(path = "/login.pg")
	public String loginPg() {
		return "jsp/app/login";
	}

	@RequestMapping(path = "/login.ax")
	public ResponseEntity loginAx(@RequestBody @Valid LoginVO loginVO) {

		log.debug("loginVO: {}", loginVO);

		SessionVO sessionVO = loginService.loginAx(loginVO);

		return ResUtil.resSucc(sessionVO.getNcnm() + "용사님 어서오세요.");

	}

	@RequestMapping(path = "/logOut.pg")
	public String logOutAx(HttpSession session) {
		session.invalidate();
		return "redirect:/idx/intro.pg";
	}

}