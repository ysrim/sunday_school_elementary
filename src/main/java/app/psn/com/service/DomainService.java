package app.psn.com.service;

import app.psn.com.vo.AvatarVO;
import app.psn.com.vo.QuestVO;
import app.psn.com.vo.RewardVO;

public interface DomainService {

	QuestVO sltQuest(int questSn);

	AvatarVO sltAvatar(int mberSn);

}