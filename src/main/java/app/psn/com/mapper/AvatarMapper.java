package app.psn.com.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import app.psn.com.vo.QuestContinuityVO;
import app.psn.com.vo.RewardVO;

@Mapper
public interface AvatarMapper {

	void udtAvatarLevel(@Param("mberSn") int mberSn, @Param("targetLevel") int targetLevel);

	int sltLevelRulesExp(int exp);

}