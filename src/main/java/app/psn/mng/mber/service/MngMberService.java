package app.psn.mng.mber.service;

import java.util.List;

import app.psn.mng.mber.vo.MngMberVO;

public interface MngMberService {

	/**
	 * 회원 목록
	 */
	List<MngMberVO> getMberList();

	/**
	 * 패스워드 초기화
	 */
	void resetPasswordAx(Integer mberSn);

}