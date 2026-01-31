package app.psn.com.service;

import app.psn.com.vo.AvatarLevelRulesVO;
import app.psn.com.vo.AvatarVO;
import app.psn.com.vo.QuestContinuityRulesVO;
import app.psn.com.vo.QuestContinuityVO;
import app.psn.com.vo.QuestVO;
import app.psn.com.vo.RewardVO;

public interface DomainService {

	QuestVO sltQuest(int questSn);

	AvatarVO sltAvatar(int mberSn);

	AvatarLevelRulesVO sltAvatarLevelRules(int level);

	QuestContinuityRulesVO sltQuestContinuityRules(int questSn);

	QuestContinuityVO sltQuestContinuity(int mberSn, int questSn);

}