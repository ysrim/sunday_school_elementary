package app.psn.mng.feed.service;

import java.util.List;

import app.psn.mng.feed.vo.MngReqFeedVO;

public interface MngFeedService {

	/**
	 * 게시물 목록
	 */
	List<MngReqFeedVO> sltFeedList();

	/**
	 * 게시물 정보
	 */
	MngReqFeedVO sltFeed(Integer postSn);

	/**
	 * 게시물 저장
	 */
	void crtFeedDo(MngReqFeedVO mngReqFeedVO);

	/**
	 * 게시물 수정
	 */
	void udtFeedDo(MngReqFeedVO mngReqFeedVO);

	/**
	 * 게시물 삭제
	 */
	void delFeedDo(Integer postSn);

}