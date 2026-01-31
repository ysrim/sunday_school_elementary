package app.psn.com.service;

import com.base.vo.QuestCompleteEvent;

import app.psn.com.vo.QuestContinuityVO;
import app.psn.com.vo.RewardVO;

public interface RewardService {

	void insMberReward(RewardVO vo);

	void processQuestRewards(QuestCompleteEvent vo);

	void insQuestContinuity(QuestContinuityVO vo);

}