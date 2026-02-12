package app.psn.tch.home.service;

import app.psn.tch.home.vo.TchGildPostVO;

import java.util.List;
import java.util.Map;

public interface TchHomeService {

	/**
	 * 대쉬보드
	 */
	Map<String, String> dashboard();

	/**
	 * 길드 메시지
	 */
	String gildMsg();

	/**
	 * 길드 메시지 등록
	 */
	void regGildMsg(String slogan);

	/**
	 * 길드 포스트 목록 가져오기
	 */
	List<TchGildPostVO> getTchGildPost();

	/**
	 * 길드 포스트 삭제
	 */
	void delGildPost(Integer postSn);

	/**
	 * 길드 포스트 등록
	 */
	void regGildPost(String content);

}