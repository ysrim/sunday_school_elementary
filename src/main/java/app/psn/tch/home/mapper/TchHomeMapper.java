package app.psn.tch.home.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

@Mapper
public interface TchHomeMapper {

	/**
	 * 대쉬보드
	 */
	Map<String, String> dashboard(@Param("guildSn") Integer guildSn);

}