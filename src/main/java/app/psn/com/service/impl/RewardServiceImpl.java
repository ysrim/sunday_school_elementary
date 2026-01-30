package app.psn.com.service.impl;

import com.base.vo.QuestCompleteEvent;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.psn.com.mapper.RewardMapper;
import app.psn.com.service.DomainService;
import app.psn.com.service.RewardService;
import app.psn.com.vo.AvatarVO;
import app.psn.com.vo.RewardVO;
import app.psn.com.vo.QuestVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("rewardService")
@RequiredArgsConstructor // 1. final 필드에 대한 생성자를 자동 생성 (생성자 주입)
@Transactional(readOnly = true) // 2. 기본적으로 읽기 전용으로 설정 (성능 최적화)
public class RewardServiceImpl implements RewardService {

    private final RewardMapper rewardMapper;

    private final DomainService domainService;

    @Override
    public void giveRewards(RewardVO reward) {

        // 3. 리워드 테이블에 인서트 한다.
    /*
    insert into MBER_REWARD_LOGS (LOG_SN, MBER_SN, REWARD_TYPE, AMOUNT, BALANCE_AFTER, CHANGE_TYPE, REFERENCE_SN, DESCRIPTION, CREAT_DE)
    MBER_SN, REWARD_TYPE, AMOUNT, BALANCE_AFTER, CHANGE_TYPE, REFERENCE_SN, DESCRIPTION
    amount는 sql로 sub query로 계산해서 insert 해야함
     */

    }

    @Override
    public void processQuestRewards(QuestCompleteEvent event) {

        // 퀘스트 정보
        QuestVO quest = domainService.sltQuest(event.questSn());
        // 포인트 지급
        this.giveRewards(RewardVO.ofPoint(event, quest));
        // 경험치 지급
        this.giveRewards(RewardVO.ofExp(event, quest));

        // 2. 연퀘 체크 및 추가 보상
        // QUEST_CONTINUITY_RULES 해당 퀘스트 정보를 조회하여 연퀘 여부를 확인 한다. 없으면 pass
        // 1. 연속 판정: 학생이 오늘 퀘스트를 완료하면 USER_QUEST_CONTINUITY 테이블을 확인합니다. 없으면 insert 있으면 update
        //   - LAST_SUCCESS_DE[마지막 성공 날짜 (연속 여부 판정용)]가 어제라면 CURRENT_STREAK[현재 연속 수행 일수]를 +1 시킵니다.
        //   - LAST_SUCCESS_DE가 오늘이라면: 이미 완료한 것이므로 변화 없음.
        //   - LAST_SUCCESS_DE가 오늘,어제가 아니면: CURRENT_STREAK[현재 연속 수행 일수]를 1로 리셋합니다.
        // 2. 보상 지급: 갱신된 CURRENT_STREAK 값이 QUEST_CONTINUITY_RULES의 CONTINUITY_DAYS와 일치하는 시점에
        //    포인트와 경험치를 지급하고 로그(EXP_LOGS, POINT_LOGS)를 남깁니다.
        // 참고. 최대 기록: CURRENT_STREAK가 MAX_STREAK보다 커지면 최대 기록도 함께 업데이트하여 나중에 '최장 연속 달성 상장' 등을 줄 때 활용할 수 있습니다.
        // 포인트 지급 method 수행

        /**
         *     MBER_SN         int                                 not null comment '학생 ID',
         *     QUEST_SN        int                                 not null comment '퀘스트 ID',
         *     CURRENT_STREAK  int       default 0                 null comment '현재 연속 수행 일수',
         *     MAX_STREAK      int       default 0                 null comment '최대 연속 수행 기록',
         *     LAST_SUCCESS_DE date                                null comment '마지막 성공 날짜 (연속 여부 판정용)',
         *     UDT_DE          timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '갱신 시간',
         */

    }

}