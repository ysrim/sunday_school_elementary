package app.idx.lgn.service;

import app.idx.lgn.vo.LoginVO;
import app.idx.lgn.vo.SessionVO;
import jakarta.servlet.http.HttpServletRequest;

public interface LoginService {

	SessionVO loginAx(LoginVO loginVO);

}