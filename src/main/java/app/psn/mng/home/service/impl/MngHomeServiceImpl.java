package app.psn.mng.home.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.psn.mng.home.mapper.MngHomeMapper;
import app.psn.mng.home.service.MngHomeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("mngHomeService")
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MngHomeServiceImpl implements MngHomeService {

	private final MngHomeMapper mngHomeMapper;

	@Override
	public Map<String, String> getAtndCnt() {

		return mngHomeMapper.getAtndCnt();

	}

	@Override
	public Map<String, String> getQestCnt() {

		return mngHomeMapper.getQestCnt();

	}

	@Override
	public Map<String, String> getWeekPoint() {

		return mngHomeMapper.getWeekPoint();

	}

	@Override
	public Map<String, String> getSumPoint() {

		return mngHomeMapper.getSumPoint();

	}

	@Override
	public List<Map<String, String>> getAtndList() {

		return mngHomeMapper.getAtndList();

	}

	@Override
	public List<Map<String, String>> getAvatarLevel() {

		return mngHomeMapper.getAvatarLevel();

	}

	@Override
	public List<Map<String, String>> getTimeVisit() {

		return mngHomeMapper.getTimeVisit();

	}

	@Override
	public List<Map<String, String>> getGradeClaAtndCnt() {

		return mngHomeMapper.getGradeClaAtndCnt();

	}

	@Override
	public List<Map<String, String>> getExcellentMber() {

		return mngHomeMapper.getExcellentMber();

	}

}