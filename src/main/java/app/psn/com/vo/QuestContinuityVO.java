package app.psn.com.vo;

import com.base.vo.QuestCompleteEvent;

/**
 * 연속 수행 퀘스트 정보
 */
public record QuestContinuityVO(Integer mberSn,            // 회원_일련번호
								Integer questSn,           // 퀘스트_일련번호
								Integer currentStreak,     // 현재 연속수행일수
								String lastSuccessDe       // 마지막 수행일
) {
	public static QuestContinuityVO ofIns(QuestCompleteEvent event, Integer currentStreak) {
		return new QuestContinuityVO( //
				event.mberSn() //
				, event.questSn() //
				, currentStreak //
				, "");
	}
}