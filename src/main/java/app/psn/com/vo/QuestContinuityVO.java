package app.psn.com.vo;

import com.base.vo.QuestCompleteEvent;

/**
 * 연속 수행 보너스 지급 규칙 정의
 */
public record QuestContinuityVO(int mberSn,            // 회원_일련번호
								int questSn,           // 퀘스트_일련번호
								int currentStreak,     // 현재 연속수행일수
								String lastSuccessDe   // 마지막 수행일
) {
	public static QuestContinuityVO ofIns(QuestCompleteEvent event, int currentStreak) {
		return new QuestContinuityVO( //
			event.mberSn() //
			, event.questSn() //
			, currentStreak //
			, "");
	}
}