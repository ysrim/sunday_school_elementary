package app.psn.std.home.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import app.psn.std.home.vo.StdHomeGildInfoVO;
import app.psn.std.home.vo.StdHomeGildVO;
import app.psn.std.home.vo.StdNoticeVO;

@Mapper
public interface StdHomeMapper {

	/**
	 * 길드원 목록
	 */
	List<StdHomeGildVO> sltGildMberList(Integer guildSn);

	/**
	 * 길드 정보
	 */
	StdHomeGildInfoVO sltGildInfo(Integer guildSn);

	/**
	 * 공지사항 목록
	 */
	List<StdNoticeVO> getNoticeList();

	/**
	 * 공지사항 게시물
	 */
	StdNoticeVO getNoticeCont(@Param("postSn") Integer postSn);

}