package app.psn.tch.home.mapper;

import app.psn.tch.home.vo.TchGildPostVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

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
	void regGildMsg(@Param("guildSn") Integer guildSn, @Param("slogan") String slogan);

	/**
	 * 길드 포스트
	 */
	List<TchGildPostVO> getTchGildPost(@Param("guildSn") Integer guildSn, @Param("mberSn") Integer mberSn);

	/**
	 * 길드 포스트 삭제
	 */
	void delGildPost(@Param("guildSn") Integer guildSn, @Param("postSn") Integer postSn);

	/**
	 * 길드 포스트 등록
	 */
	void regGildPost(@Param("guildSn") Integer guildSn, @Param("mberSn") Integer mberSn, @Param("content") String content);


}