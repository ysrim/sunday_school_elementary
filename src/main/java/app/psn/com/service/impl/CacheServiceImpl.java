package app.psn.com.service.impl;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.psn.com.mapper.CacheMapper;
import app.psn.com.service.CacheService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("cacheService")
@RequiredArgsConstructor // 1. final 필드에 대한 생성자를 자동 생성 (생성자 주입)
@Transactional(readOnly = true) // 2. 기본적으로 읽기 전용으로 설정 (성능 최적화)
public class CacheServiceImpl implements CacheService {

	private final CacheMapper cacheMapper;

	@Cacheable(value = "mberPoint", key = "#p0")
	@Override
	public int sltPont(int mberSn) {
		return cacheMapper.sltPont(mberSn);
	}

	@Cacheable(value = "mberLv", key = "#p0")
	@Override
	public int sltLv(int mberSn) {
		return cacheMapper.sltLv(mberSn);
	}

	@Cacheable(value = "mberExp", key = "#p0")
	@Override
	public int sltExp(int mberSn) {
		return cacheMapper.sltExp(mberSn);
	}

	// ex. @Cacheable(value = "boardList", key = "#searchType + '_' + #keyword")
}