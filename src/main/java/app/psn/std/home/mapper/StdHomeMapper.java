package app.psn.std.home.mapper;

import app.psn.std.home.vo.StdHomeGildInfoVO;
import app.psn.std.home.vo.StdHomeGildVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

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

}