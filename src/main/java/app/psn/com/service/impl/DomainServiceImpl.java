package app.psn.com.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.psn.com.mapper.DomainMapper;
import app.psn.com.mapper.RewardMapper;
import app.psn.com.service.DomainService;
import app.psn.com.service.RewardService;
import app.psn.com.vo.AvatarVO;
import app.psn.com.vo.QuestVO;
import app.psn.com.vo.RewardVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("domainService")
@RequiredArgsConstructor // 1. final 필드에 대한 생성자를 자동 생성 (생성자 주입)
@Transactional(readOnly = true) // 2. 기본적으로 읽기 전용으로 설정 (성능 최적화)
public class DomainServiceImpl implements DomainService {

	private final DomainMapper domainMapper;

	@Override
	public QuestVO sltQuest(int questSn) {
		return domainMapper.sltQuest(questSn);
	}

	@Override
	public AvatarVO sltAvatar(int mberSn) {
		return domainMapper.sltAvatar(mberSn);
	}

}