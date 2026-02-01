package com.base.vo;

public record ToastMsgEvent( // 토스트 매시지 이벤트 리스너
							 int mberSn,       // 회원_일련번호
							 String toastType, // 토스트_타입('REWARD','SUCCESS','WARNING','INFO')
							 String title,     // 제목
							 String message    // 메시지
) {
}