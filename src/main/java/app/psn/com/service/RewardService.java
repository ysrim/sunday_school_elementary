package app.psn.com.service;

import app.psn.com.vo.QuestContinuityVO;
import app.psn.com.vo.RewardVO;
import com.base.vo.QuestCompleteEvent;

public interface RewardService {

	/**
	 * 리워드 지급
	 */
	void insMberReward(RewardVO vo);

	/**
	 * 퀘스트 수행에 따른 리워드 지급 프로세스
	 */
	void processQuestRewards(QuestCompleteEvent vo);

	/**
	 * 퀘스트 연속퀘 수행정보 저장
	 */
	void insQuestContinuity(QuestContinuityVO vo);

}