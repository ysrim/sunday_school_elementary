package app.psn.tch.gild.mapper;

import app.psn.tch.gild.vo.TchGildMemberVO;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TchGildMapper {

	/**
	 * 길드원 목록
	 */
	List<TchGildMemberVO> sltTchGuildMberList(@Param("guildSn") Integer guildSn);

}