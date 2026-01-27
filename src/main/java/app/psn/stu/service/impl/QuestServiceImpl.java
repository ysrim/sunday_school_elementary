package app.psn.stu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.base.utl.SessionUtil;
import com.base.utl.StringUtil;

import app.idx.lgn.vo.SessionVO;
import app.psn.stu.mapper.OptionMapper;
import app.psn.stu.mapper.QuestMapper;
import app.psn.stu.service.OptionService;
import app.psn.stu.service.QuestService;
import app.psn.stu.vo.QuestListVO;
import app.psn.stu.vo.QuestVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("questService")
@RequiredArgsConstructor // 1. final 필드에 대한 생성자를 자동 생성 (생성자 주입)
@Transactional(readOnly = true) // 2. 기본적으로 읽기 전용으로 설정 (성능 최적화)
public class QuestServiceImpl implements QuestService {

	private final QuestMapper questMapper;

	private @Value("#{globalsProps['secretKey.key.qr']}") String secretKey;

	@Override
	public List<QuestListVO> sltQuestList() {
		return questMapper.sltQuestList(SessionUtil.getMberInfo().getMberSn() + "");
	}

	public boolean questDo(QuestVO questVO) {
		int cnt = questMapper.questDo(questVO);
		log.info("insert count: {}", cnt);
		if (cnt < 1) { // 비즈니스 로직상 필수라면 예외 처리
			throw new RuntimeException("퀘스트 수행 내역 저장 중 오류가 발생했습니다.");
		}
		return true;
	}
}