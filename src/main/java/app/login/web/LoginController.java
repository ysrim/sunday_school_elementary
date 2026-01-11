package app.login.web;

import java.util.Arrays;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.base.utl.ResUtil;
import com.base.vo.BodyResVO;

import app.login.service.LoginService;
import app.login.vo.LoginVO;
import app.login.vo.SessionVO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class LoginController {

	@Resource(name = "loginService")
	private LoginService loginService;

	@RequestMapping(path = "login.pg")
	public String loginPg() {
		return "jsp/app/login";
	}

	@RequestMapping(path = "login.ax")
	public ResponseEntity loginAx(BodyResVO bodyResVO, HttpServletRequest req, @RequestBody @Valid LoginVO loginVO) {

		log.debug("loginVO: {}", loginVO);

		SessionVO sessionVO = loginService.loginAx(req, loginVO);

		return ResUtil.resSucc(bodyResVO, sessionVO.getNcnm() + "용사님 어서오세요.");

	}

	@GetMapping("/dashboard.pg")
	public String dashboard(Model model) {
		model.addAttribute("items", Arrays.asList("데이터1", "데이터2"));

		// "th/" 패턴이므로 Thymeleaf가 처리
		// 실제 파일: /WEB-INF/templates/th/dashboard.html
		// 이 파일을 열면 layout:decorate 설정 때문에 defaultLayout과 합쳐져서 렌더링됨

		return "student/dashboard";
	}

}