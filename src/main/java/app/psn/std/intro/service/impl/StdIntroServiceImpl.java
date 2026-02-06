package app.psn.std.intro.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.psn.std.intro.mapper.StdIntroMapper;
import app.psn.std.intro.service.StdIntroService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("stdIntroService")
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StdIntroServiceImpl implements StdIntroService {

	private final StdIntroMapper stdIntroMapper;

	@Override
	public Integer stdMberCnt() {

		return stdIntroMapper.sltStdMberCnt();
		
	}

}