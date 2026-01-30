package app.psn.com.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import app.psn.com.vo.AvatarVO;
import app.psn.com.vo.QuestContinuityRulesVO;
import app.psn.com.vo.QuestContinuityVO;
import app.psn.com.vo.QuestVO;
import app.psn.com.vo.RewardVO;

@Mapper
public interface DomainMapper {

	QuestVO sltQuest(int questSn);

	AvatarVO sltAvatar(int mberSn);

	QuestContinuityRulesVO sltQuestContinuityRules(int questSn);

	QuestContinuityVO sltQuestContinuity(@Param("mberSn") int mberSn, @Param("questSn") int questSn);
}