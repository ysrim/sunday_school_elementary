package app.psn.mng.feed.service;

import java.util.List;

import app.psn.mng.feed.vo.MngReqFeedVO;
import app.psn.mng.feed.vo.MngResFeedVO;

public interface MngFeedService {

	/**
	 * 게시물 목록
	 */
	List<MngResFeedVO> sltFeedList();

	/**
	 * 게시물 정보
	 */
	MngResFeedVO sltFeed(Integer postSn);

	/**
	 * 게시물 저장
	 */
	void crtFeedDo(MngReqFeedVO mngReqFeedVO);

	/**
	 * 게시물 수정
	 */
	void udtFeedDo(MngReqFeedVO mngReqFeedVO);

}