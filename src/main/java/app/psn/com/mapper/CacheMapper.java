package app.psn.com.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CacheMapper {

	int sltPont(int mberSn); // 캐쉬데이터 달란트

	int sltLv(int mberSn); // 캐쉬데이터 레벨

	int sltExp(int mberSn); // 캐쉬데이터 경험치

}