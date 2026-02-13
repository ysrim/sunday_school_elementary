package app.psn.com.mapper;

import app.psn.com.vo.QuestContinuityVO;
import app.psn.com.vo.RewardVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RewardMapper {

	void insMberRewardLogs(RewardVO vo);

	void insQuestContinuity(QuestContinuityVO vo);

	void updateAvatarAmount(RewardVO reward);

}