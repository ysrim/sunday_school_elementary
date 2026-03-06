package app.psn.com.service;

import app.psn.com.vo.AvatarLevelRulesVO;
import app.psn.com.vo.AvatarVO;
import app.psn.com.vo.QuestContinuityRulesVO;
import app.psn.com.vo.QuestContinuityVO;
import app.psn.com.vo.QuestLogsVO;
import app.psn.com.vo.QuestVO;
import app.psn.com.vo.VisitLogVO;

public interface DomainService {

	/**
	 * 퀘스트 정보
	 */
	QuestVO sltQuest(Integer questSn);

	/**
	 * 아바타 정보
	 */
	AvatarVO sltAvatar(Integer mberSn);

	/**
	 * 아바타 레벨업 필요 레벨정보
	 */
	AvatarLevelRulesVO sltAvatarLevelRules(Integer level);

	/**
	 * 퀘스트 연퀘 룰정보 (몇 연속퀘인지 정보를 담고 있음)
	 */
	QuestContinuityRulesVO sltQuestContinuityRules(Integer questSn);

	/**
	 * 퀘스트 연퀘 몇번 했는지 여부
	 */
	QuestContinuityVO sltQuestContinuity(Integer mberSn, Integer questSn);

	/**
	 * 퀘스트 로그 정보
	 */
	QuestLogsVO sltQuestLogs(Integer logSn);

	/**
	 * 방문자 로그 저장
	 */
	void regVisitLog(VisitLogVO visitLogVO);

}