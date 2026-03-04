package app.psn.mng.feed.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import app.psn.mng.feed.vo.MngReqFeedVO;

@Mapper
public interface MngFeedMapper {

	/**
	 * 게시물 목록
	 */
	List<MngReqFeedVO> sltFeedList();

	/**
	 * 게시물 정보
	 */
	MngReqFeedVO sltFeed(@Param("postSn") Integer postSn);

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
	void delFeedDo(@Param("postSn") Integer postSn);

}