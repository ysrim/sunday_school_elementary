package app.psn.com.mapper;

import org.apache.ibatis.annotations.Mapper;

import app.psn.com.vo.QuestContinuityVO;
import app.psn.com.vo.RewardVO;

@Mapper
public interface RewardMapper {

	void insMberRewardLogs(RewardVO vo);

	void insQuestContinuity(QuestContinuityVO vo);

	void updateAvatarAmount(RewardVO reward);

}