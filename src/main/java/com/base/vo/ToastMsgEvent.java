package com.base.vo;

public record ToastMsgEvent( // 퀘스트 완료 이벤트 리스너
							 int mberSn,     // 회원_일련번호
							 String title,   // 제목
							 String message, // 메시지
							 String emoji   // 이모지
) {
}