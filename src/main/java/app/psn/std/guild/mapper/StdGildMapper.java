package app.psn.std.guild.mapper;

import app.psn.std.guild.vo.StdGildPostVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StdGildMapper {

	/**
	 * 길드 포스트
	 */
	List<StdGildPostVO> getTchGildPost(@Param("guildSn") Integer guildSn, @Param("mberSn") Integer mberSn);

	/**
	 * 길드 포스트 삭제
	 */
	void delGildPost(@Param("postSn") Integer postSn, @Param("mberSn") Integer mberSn);

	/**
	 * 길드 포스트 등록
	 */
	void regGildPost(@Param("guildSn") Integer guildSn, @Param("mberSn") Integer mberSn, @Param("content") String content);


}