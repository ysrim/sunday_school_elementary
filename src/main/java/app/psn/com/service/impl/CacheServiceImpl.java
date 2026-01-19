package app.psn.com.service.impl;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.psn.com.mapper.CacheMapper;
import app.psn.com.service.CacheService;
import app.psn.com.vo.TodayBibleVerseVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("cacheService")
@RequiredArgsConstructor // 1. final 필드에 대한 생성자를 자동 생성 (생성자 주입)
@Transactional(readOnly = true) // 2. 기본적으로 읽기 전용으로 설정 (성능 최적화)
public class CacheServiceImpl implements CacheService {

	private final CacheMapper cacheMapper;

	private final CacheManager cacheManager;

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

	@Cacheable(value = "todayBibleVerse")
	@Override
	public TodayBibleVerseVO sltTodayBibleVerse() {
		return cacheMapper.sltTodayBibleVerse();
	}

	@Cacheable(value = "onlineMbers", key = "#p0")
	@Override
	public boolean addOnlineMber(String mberId) {
		return true;
	}

	@CacheEvict(value = "onlineMbers", key = "#p0")
	@Override
	public void delOnlineMber(String mberId) {
		// do something;
	}

	public boolean checkKeyExists(String cacheName, String key) {

		// 1. 캐시 저장소 가져오기 (예: "onlineMbers")
		Cache cache = cacheManager.getCache(cacheName);
		if (cache == null) {
			return false; // 캐시 저장소 자체가 없음
		}
		// 2. 값 꺼내보기 (값이 null이 아니면 키가 존재하는 것)
		// 주의: Spring의 ValueWrapper를 통해 확인하는 것이 가장 안전함
		Cache.ValueWrapper wrapper = cache.get(key);

		return wrapper != null;

	}

	//삭제
	// @CacheEvict(value = "boardList", allEntries = true)
	// @CacheEvict(value = "boardList", key = "#searchType + '_' + #keyword")
	//추가
	// ex. @Cacheable(value = "boardList", key = "#searchType + '_' + #keyword")

}