package app.psn.com.mapper;

import org.apache.ibatis.annotations.Mapper;

import app.psn.com.vo.AvatarLevelVO;
import app.psn.com.vo.TodayBibleVerseVO;

@Mapper
public interface CacheMapper {

	int sltPont(int mberSn); // 캐쉬데이터 달란트

	int sltLevel(int mberSn); // 캐쉬데이터 레벨

	AvatarLevelVO sltExp(int mberSn); // 캐쉬데이터 경험치

	TodayBibleVerseVO sltTodayBibleVerse(); // 오늘의 성경말씀

}