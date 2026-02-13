package app.psn.com.mapper;

import app.psn.com.vo.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface DomainMapper {

	QuestVO sltQuest(Integer questSn);

	AvatarVO sltAvatar(Integer mberSn);

	AvatarLevelRulesVO sltAvatarLevelRules(Integer level);

	QuestContinuityRulesVO sltQuestContinuityRules(Integer questSn);

	QuestContinuityVO sltQuestContinuity(@Param("mberSn") Integer mberSn, @Param("questSn") Integer questSn);

	QuestLogsVO sltQuestLogs(Integer logSn);

}