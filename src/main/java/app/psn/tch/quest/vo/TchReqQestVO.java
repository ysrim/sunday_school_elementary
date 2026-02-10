package app.psn.tch.quest.vo;

import com.base.enumm.com.QuestTypeEnum;

/**
 * 퀘스트승인 정보
 */
public record TchReqQestVO( // 퀘스트 로그 정보
							Integer logSn,             // 퀘스트_수행_일련번호
							Integer questSn,           // 퀘스트_일련번호
							Integer mberSn,            // 회원_일련번호
							String creatDate,          // 퀘스트_수행_일자 '%m-%d %H:%I'
							QuestTypeEnum questType,   // 퀘스트_타입 enum('DAILY','WEEKLY','EVENT','ONE_TIME')
							String title,              // 퀘스트_제목
							String description,        // 퀘스트_설명
							String emoji,              // 이모지
							String rewardExp,          // 보상_경험치
							String rewardPoint,         // 보상_달란트
							String processedDate,       // 승인_일자'%m-%d %H:%I'
							String mberNm,              // 회원이름
							String status               // 회원이름
) {
}