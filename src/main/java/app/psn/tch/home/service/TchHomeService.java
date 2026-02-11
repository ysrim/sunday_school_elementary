package app.psn.tch.home.service;

import java.util.List;
import java.util.Map;

import app.psn.tch.home.vo.TchGildPostVO;

public interface TchHomeService {

	/**
	 * 대쉬보드
	 */
	Map<String, String> dashboard();

	/**
	 * 길드 메시지
	 */
	String gildMsg();

	void saveGildMsgAx(String slogan);

	List<TchGildPostVO> getTchGildPost();

}