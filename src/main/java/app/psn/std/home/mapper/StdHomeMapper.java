package app.psn.std.home.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import app.psn.std.home.vo.StdHomeGuildInfoVO;
import app.psn.std.home.vo.StdHomeGuildListVO;

@Mapper
public interface StdHomeMapper {

	/**
	 * 길드원 목록
	 */
	List<StdHomeGuildListVO> sltGuildMberList(Integer guildSn);

	/**
	 * 길드 정보
	 */
	StdHomeGuildInfoVO sltGuildInfo(Integer guildSn);

}