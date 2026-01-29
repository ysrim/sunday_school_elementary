package com.base.vo;

public record QuestCompleteEvent( // 퀘스트 완료 이벤트
								  int mberSn,  // 회원_일련번호
								  int questSn, // 퀘스트_일련번호
								  int logSn    // 퀘스트완료_일련번호
) {
}
