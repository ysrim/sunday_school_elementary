package app.idx.man.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.idx.man.mapper.IntroMapper;
import app.idx.man.service.IntroService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("introService")
@RequiredArgsConstructor // 1. final 필드에 대한 생성자를 자동 생성 (생성자 주입)
@Transactional(readOnly = true) // 2. 기본적으로 읽기 전용으로 설정 (성능 최적화)
public class IntroServiceImpl implements IntroService {

	//private @Value("#{globalsProps['bc.contractAddr.mov']}") String contractAddr;

	private final IntroMapper introMapper;

	@Override
	public int stuMberCnt() {
		return introMapper.sltStuMberCnt();
	}
}