package app.psn.tch.login.service;

import app.psn.tch.login.vo.TchLoginVO;
import app.psn.tch.login.vo.TchSessionVO;

public interface TchLoginService {

	/**
	 * 선생님 사용자 정보 요청
	 */
	TchSessionVO sltMber(TchLoginVO loginVO);

	/**
	 * 로그인 요청
	 */
	TchSessionVO loginAx(TchLoginVO loginVO);

}