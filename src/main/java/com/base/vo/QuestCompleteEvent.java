package com.base.vo;

public record QuestCompleteEvent( // 퀘스트 완료 이벤트 리스너
								  Integer mberSn,  // 회원_일련번호
								  Integer questSn, // 퀘스트_일련번호
								  Integer logSn    // 퀘스트완료_일련번호(추후구현)
) {
}
