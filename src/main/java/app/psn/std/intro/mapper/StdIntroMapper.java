package app.psn.std.intro.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StdIntroMapper {

	/**
	 * 학생 가입자 전체 수
	 */
	Integer sltStdMberCnt();

}