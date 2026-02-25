package app.psn.mng.mber.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.psn.mng.mber.mapper.MngMberMapper;
import app.psn.mng.mber.service.MngMberService;
import app.psn.mng.mber.vo.MngMberVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("mngMberService")
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MngMberServiceImpl implements MngMberService {

	private final MngMberMapper mngMberMapper;

	@Override
	public List<MngMberVO> getMberList() {

		return mngMberMapper.getMberList();

	}

}