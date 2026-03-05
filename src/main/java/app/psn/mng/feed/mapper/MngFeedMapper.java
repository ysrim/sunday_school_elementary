package app.psn.mng.feed.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import app.psn.mng.feed.vo.MngReqFeedVO;
import app.psn.mng.feed.vo.MngResFeedVO;

@Mapper
public interface MngFeedMapper {

	/**
	 * 게시물 목록
	 */
	List<MngResFeedVO> sltFeedList();

	/**
	 * 게시물 정보
	 */
	MngResFeedVO sltFeed(@Param("postSn") Integer postSn);

	/**
	 * 게시물 저장
	 */
	void crtFeedDo(MngReqFeedVO mngReqFeedVO);

	/**
	 * 게시물 수정
	 */
	void udtFeedDo(MngReqFeedVO mngReqFeedVO);

}