package app.psn.std.gild.service;

import app.psn.std.gild.vo.StdGildPostVO;

import java.util.List;

public interface StdGildService {

	/**
	 * 길드 포스트 목록 가져오기
	 */
	List<StdGildPostVO> getTchGildPost();

	/**
	 * 길드 포스트 삭제
	 */
	void delGildPost(Integer postSn);

	/**
	 * 길드 포스트 등록
	 */
	void regGildPost(String content);

}