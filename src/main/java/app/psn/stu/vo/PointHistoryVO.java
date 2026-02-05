package app.psn.stu.vo;

import com.base.enumm.RewardGetPathEnum;

public record PointHistoryVO( // 출석
                              String type,          // 타입 (+, -)
                              String changeType,    // 획득/쓰기 경로
                              String emoji,         // 이모지
                              String description,   // 사용내역 상세
                              String creatDe,       // 생성날짜
                              Integer amount,       // 금액(+,-)
                              Integer balanceAfter  // 남은 최종 금액
) {
    public PointHistoryVO {
        // 1. changeType이 Enum 이름("QUEST")으로 들어온다고 가정
        if (changeType != null) {
            RewardGetPathEnum pathEnum = RewardGetPathEnum.findByName(changeType);
            // 2. 매칭되는 Enum이 있다면, 한글 명칭("퀘스트")으로 값을 교체
            if (pathEnum != null) {
                changeType = pathEnum.getRewardGetName();
            }
        }
        if (amount == null) amount = 0;
    }
}