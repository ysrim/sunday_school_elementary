package app.psn.com.mapper;

import org.apache.ibatis.annotations.Mapper;

import app.psn.com.vo.AvatarVO;
import app.psn.com.vo.QuestContinuityVO;
import app.psn.com.vo.QuestVO;
import app.psn.com.vo.RewardVO;

@Mapper
public interface RewardMapper {

	int updateReward(RewardVO vo); // 아바타 정보 리워드 정보 갱신

	void insMberRewardLogs(RewardVO vo);

	void insQuestContinuity(QuestContinuityVO vo);
}