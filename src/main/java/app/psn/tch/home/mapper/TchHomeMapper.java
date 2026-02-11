package app.psn.tch.home.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

import app.psn.tch.home.vo.TchGildPostVO;

@Mapper
public interface TchHomeMapper {

	/**
	 * 대쉬보드
	 */
	Map<String, String> dashboard(@Param("guildSn") Integer guildSn);

	/**
	 * 길드메시지
	 */
	String gildMsg(@Param("guildSn") Integer guildSn);

	/**
	 * 길드메시지 저장
	 */
	void saveGildMsgAx(@Param("guildSn") Integer guildSn, @Param("slogan") String slogan);

	/**
	 * 길드 포스트
	 */
	List<TchGildPostVO> getTchGildPost(@Param("guildSn") Integer guildSn);

}