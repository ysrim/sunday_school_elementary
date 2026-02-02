package app.idx.man.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.idx.man.mapper.IntroMapper;
import app.idx.man.service.IntroService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("introService")
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class IntroServiceImpl implements IntroService {

	//private @Value("#{globalsProps['bc.contractAddr.mov']}") String contractAddr;

	private final IntroMapper introMapper;

	@Override
	public int stuMberCnt() {
		return introMapper.sltStuMberCnt();
	}
}