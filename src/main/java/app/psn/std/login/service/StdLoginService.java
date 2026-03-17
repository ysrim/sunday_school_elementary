package app.psn.std.login.service;

import app.psn.com.vo.LoginVO;
import app.psn.std.login.vo.StdSessionVO;
import jakarta.servlet.http.HttpServletRequest;

public interface StdLoginService {

	/**
	 * 학생 사용자 정보 요청
	 */
	StdSessionVO sltMber(LoginVO loginVO);

	/**
	 * 로그인 요청
	 */
	StdSessionVO loginAx(LoginVO loginVO);

	/**
	 * 엑세스토큰 리프래쉬
	 */
	String refreshToken(HttpServletRequest request);

}