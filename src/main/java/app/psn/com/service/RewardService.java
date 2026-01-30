package app.psn.com.service;

import app.psn.com.vo.RewardVO;
import com.base.vo.QuestCompleteEvent;

public interface RewardService {

    void giveRewards(RewardVO reward);

    public void processQuestRewards(QuestCompleteEvent event);

}