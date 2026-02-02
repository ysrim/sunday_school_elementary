package app.psn.stu.service.impl;

import java.util.List;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.base.utl.SessionUtil;
import com.base.vo.QuestCompleteEvent;

import app.psn.stu.mapper.QuestMapper;
import app.psn.stu.service.QuestService;
import app.psn.stu.vo.QuestListVO;
import app.psn.stu.vo.QuestPendingVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("questService")
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QuestServiceImpl implements QuestService {

	private final ApplicationEventPublisher publisher;

	private final QuestMapper questMapper;

	@Override
	public List<QuestListVO> sltQuestList() {
		return questMapper.sltQuestList(SessionUtil.getMberInfo().getMberSn() + "");
	}

	public boolean questDo(QuestPendingVO questVO) {
		int cnt = questMapper.questDo(questVO);
		log.info("insert count: {}", cnt);
		if (cnt < 1) { // 비즈니스 로직상 필수라면 예외 처리
			throw new RuntimeException("퀘스트 수행 내역 저장 중 오류가 발생했습니다.");
		}
		publisher.publishEvent(new QuestCompleteEvent(questVO.getMberSn(), questVO.getQuestSn(), 1));
		return true;
	}
}