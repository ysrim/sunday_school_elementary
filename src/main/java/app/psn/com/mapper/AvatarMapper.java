package app.psn.com.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AvatarMapper {

	void udtAvatarLevel(@Param("mberSn") int mberSn, @Param("targetLevel") int targetLevel);

	int sltLevelRulesExp(int exp);

}