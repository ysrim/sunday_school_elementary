package app.psn.tch.home.mapper;

import app.psn.tch.home.vo.StdHomeGuildInfoVO;
import app.psn.tch.home.vo.StdHomeGuildListVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TchHomeMapper {

	/**
	 * 길드원 목록
	 */
	List<StdHomeGuildListVO> sltGuildMberList(Integer guildSn);

	/**
	 * 길드 정보
	 */
	StdHomeGuildInfoVO sltGuildInfo(Integer guildSn);

}