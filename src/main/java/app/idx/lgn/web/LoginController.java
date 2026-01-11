package app.idx.lgn.web;

import java.util.Arrays;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.base.utl.ResUtil;
import com.base.utl.SessionUtil;
import com.base.vo.BodyResVO;

import app.idx.lgn.service.LoginService;
import app.idx.lgn.vo.LoginVO;
import app.idx.lgn.vo.SessionVO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
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
	public ResponseEntity loginAx(BodyResVO bodyResVO, @RequestBody @Valid LoginVO loginVO) {

		log.debug("loginVO: {}", loginVO);

		SessionVO sessionVO = loginService.loginAx(loginVO);

		return ResUtil.resSucc(bodyResVO, sessionVO.getNcnm() + "용사님 어서오세요.");

	}

	@RequestMapping(path = "/logOut.pg")
	public String logOutAx(BodyResVO bodyResVO, @RequestBody @Valid LoginVO loginVO) {

		SessionUtil.getSession().invalidate();

		return "redirect:/idx/intro.html";

	}

}