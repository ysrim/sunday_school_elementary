package app.psn.tch.home.vo;

import com.base.enumm.com.QuestTypeEnum;

/**
 * 퀘스트승인 정보
 */
public record TchGildPostVO( // 퀘스트 로그 정보
                             Integer postSn,           // 길드포스트_일련번호
                             Integer guildSn,          // 길드_일련번호
                             Integer mberSn,           // 회원_일련번호
                             String mberNm,            // 회원이름
                             String content,           // 내용
                             String ntcYn,             // 공지여부
                             String occpCode,          // 직업코드
                             String sexdstn,           // 성별
                             String creatDe          // 퀘스트_수행_일자 '%m-%d %H:%I'
) {
}