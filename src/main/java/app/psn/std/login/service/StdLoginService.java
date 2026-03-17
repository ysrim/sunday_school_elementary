package app.psn.std.login.service;

import app.psn.com.vo.LoginVO;
import app.psn.std.login.vo.StdSessionVO;

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
	boolean refreshTokenValid(String refreshToken);

	/**
	 * 학생 사용자 정보 요청
	 */
	StdSessionVO sltTokenMber(String refreshToken);

	/**
	 * 리프레쉬 토큰 DB 저장
	 */
	void regRefreshToken(String mberId, String refreshToken);

}