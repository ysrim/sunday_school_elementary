package app.psn.com.mapper;

import app.psn.com.vo.AvatarLevelVO;
import app.psn.com.vo.TodayBibleVerseVO;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CacheMapper {

	Integer sltPont(@Param("mberSn") Integer mberSn); // 캐쉬데이터 달란트

	Integer sltLevel(@Param("mberSn") Integer mberSn); // 캐쉬데이터 레벨

	AvatarLevelVO sltExp(@Param("mberSn") Integer mberSn); // 캐쉬데이터 경험치

	TodayBibleVerseVO sltTodayBibleVerse(); // 오늘의 성경말씀

	String getGildMsg(@Param("guildSn") Integer guildSn);

}