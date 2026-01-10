package app.login.service;

import app.join.vo.JoinMemberVO;
import app.login.vo.LoginVO;
import app.login.vo.SessionVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public interface LoginService {

	SessionVO loginAx(HttpServletRequest req, LoginVO loginVO);

}