package app.psn.com.vo;

import com.base.enumm.RewardGetPathEnum;
import com.base.enumm.RewardTypeEnum;
import com.base.vo.QuestCompleteEvent;

public record RewardVO( //
                        int mberSn,       // 지급할 회원
                        String rewardType,      // 자산 유형 ('POINT', 'EXP')
                        int amount,   // 획득량
                        String changeType, // 획득경로('QUEST', 'ADMIN_MANUAL', 'EVENT')
                        int referenceSn, // 관련ID(ex. 퀘스트로그테이블의 logSn)
                        String description // 상세내용
) {
    public static RewardVO ofPoint(QuestCompleteEvent event, QuestVO quest) {
        return new RewardVO(event.mberSn(), RewardTypeEnum.POINT.name(),
                quest.rewardPoint(), RewardGetPathEnum.QUEST.name(),
                event.logSn(), quest.description());
    }

    public static RewardVO ofExp(QuestCompleteEvent event, QuestVO quest) {
        return new RewardVO(event.mberSn(), RewardTypeEnum.EXP.name(),
                quest.rewardExp(), RewardGetPathEnum.QUEST.name(),
                event.logSn(), quest.description());
    }
    
}