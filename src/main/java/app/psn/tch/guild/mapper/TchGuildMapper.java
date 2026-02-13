package app.psn.tch.guild.mapper;

import app.psn.tch.guild.vo.TchGuildMemberVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TchGuildMapper {

	/**
	 * 길드원 목록
	 */
	List<TchGuildMemberVO> sltTchGuildMberList(@Param("guildSn") Integer guildSn);

}