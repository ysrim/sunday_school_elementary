package app.psn.com.vo;

import java.time.LocalDateTime;

public record ToastMsgVO( // 토스트 매시지
						  int toastSn,           // 회원_일련번호
						  int mberSn,            // 회원_일련번호
						  String toastType,      // 토스트_타입('REWARD','SUCCESS','WARNING','INFO')
						  String title,          // 제목
						  String message,        // 메시지
						  String isRead,         // 읽음_여부(Y/N)
						  LocalDateTime creatDe, // 생성_시간
						  LocalDateTime readDe   // 메시지_확인_시간
) {
}