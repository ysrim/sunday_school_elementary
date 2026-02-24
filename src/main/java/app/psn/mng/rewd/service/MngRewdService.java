package app.psn.mng.rewd.service;

import java.util.List;

import app.psn.mng.rewd.vo.MngRewdHistVO;
import app.psn.mng.rewd.vo.MngStdVO;

public interface MngRewdService {

	/**
	 * 학생 목록
	 */
	List<MngStdVO> getStdList();

	/**
	 * 리워드 이력
	 */
	List<MngRewdHistVO> getRewdHistList();

}