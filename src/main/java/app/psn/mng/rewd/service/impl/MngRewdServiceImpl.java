package app.psn.mng.rewd.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.psn.mng.rewd.mapper.MngRewdMapper;
import app.psn.mng.rewd.service.MngRewdService;
import app.psn.mng.rewd.vo.MngStdVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("mngRewdService")
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MngRewdServiceImpl implements MngRewdService {

	private final MngRewdMapper mngRewdMapper;

	@Override
	public List<MngStdVO> getStdList() {

		return mngRewdMapper.getStdList();

	}

}