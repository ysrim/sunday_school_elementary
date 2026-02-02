package app.psn.com.service.impl;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.base.enumm.CacheKeys;

import app.psn.com.mapper.CacheMapper;
import app.psn.com.service.CacheService;
import app.psn.com.vo.AvatarLevelVO;
import app.psn.com.vo.TodayBibleVerseVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("cacheService")
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CacheServiceImpl implements CacheService {

	private final CacheMapper cacheMapper;

	private final CacheManager cacheManager;

	@Cacheable(value = CacheKeys.MberPointEnum, key = "#p0")
	@Override
	public Integer sltPont(Integer mberSn) {
		return cacheMapper.sltPont(mberSn);
	}

	@CacheEvict(value = CacheKeys.MberPointEnum, key = "#p0")
	@Override
	public void evictPont(Integer mberSn) {
	}

	@Cacheable(value = CacheKeys.MberLvEnum, key = "#p0")
	@Override
	public Integer sltLevel(Integer mberSn) {
		return cacheMapper.sltLevel(mberSn);
	}

	@CacheEvict(value = CacheKeys.MberLvEnum, key = "#p0")
	@Override
	public void evictLevel(Integer mberSn) {
	}

	@Cacheable(value = CacheKeys.MberExpEnum, key = "#p0")
	@Override
	public AvatarLevelVO sltExp(Integer mberSn) {
		return cacheMapper.sltExp(mberSn);
	}

	@CacheEvict(value = CacheKeys.MberExpEnum, key = "#p0")
	@Override
	public void evictExp(Integer mberSn) {
	}

	@Cacheable(value = CacheKeys.TodayBibleVerseEnum)
	@Override
	public TodayBibleVerseVO sltTodayBibleVerse() {
		return cacheMapper.sltTodayBibleVerse();
	}

	@Cacheable(value = CacheKeys.OnlineMbersEnum, key = "#p0")
	@Override
	public boolean addOnlineMber(String mberId) {
		return true;
	}

	@CacheEvict(value = CacheKeys.OnlineMbersEnum, key = "#p0")
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
		Cache.ValueWrapper wrapper = cache.get(key);

		return wrapper != null;

	}

	@Override
	public Integer checkKeySize(String cacheName) {
		Cache springCache = cacheManager.getCache(cacheName);
		if (springCache != null) {
			com.github.benmanes.caffeine.cache.Cache nativeCache = (com.github.benmanes.caffeine.cache.Cache)springCache.getNativeCache();
			try {
				long bigValue = nativeCache.estimatedSize();
				Integer intValue = Math.toIntExact(bigValue); // 여기서 예외 발생
				return intValue;
			} catch (ArithmeticException e) {
				throw new RuntimeException("시스템 오류" + e.getMessage());
			}
		}
		return 0;
	}

	//삭제
	// @CacheEvict(value = "boardList", allEntries = true)
	// @CacheEvict(value = "boardList", key = "#searchType + '_' + #keyword")
	//추가
	// ex. @Cacheable(value = "boardList", key = "#searchType + '_' + #keyword")

}