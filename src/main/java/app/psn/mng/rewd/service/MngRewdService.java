package app.psn.mng.rewd.service;

import java.util.List;

import app.psn.mng.rewd.vo.MngStdVO;

public interface MngRewdService {

	/**
	 * 학생
	 */
	List<MngStdVO> getStdList();

}