package app.psn.com.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AvatarMapper {

	void udtAvatarLevel(@Param("mberSn") Integer mberSn, @Param("targetLevel") Integer targetLevel);

	Integer sltLevelRulesExp(Integer exp);

}