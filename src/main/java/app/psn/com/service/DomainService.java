package app.psn.com.service;

import app.psn.com.vo.AvatarLevelRulesVO;
import app.psn.com.vo.AvatarVO;
import app.psn.com.vo.QuestContinuityRulesVO;
import app.psn.com.vo.QuestContinuityVO;
import app.psn.com.vo.QuestVO;
import app.psn.com.vo.ToastMsgVO;

public interface DomainService {

	QuestVO sltQuest(Integer questSn);

	AvatarVO sltAvatar(Integer mberSn);

	AvatarLevelRulesVO sltAvatarLevelRules(Integer level);

	QuestContinuityRulesVO sltQuestContinuityRules(Integer questSn);

	QuestContinuityVO sltQuestContinuity(Integer mberSn, Integer questSn);

}