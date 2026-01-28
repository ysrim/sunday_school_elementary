package com.config.context;

import com.base.enumm.CacheKeys;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

@EnableCaching // 캐시 기능 활성화
@Configuration
public class CacheConfig {

	@Bean
	public CacheManager cacheManager() {

		SimpleCacheManager cacheManager = new SimpleCacheManager();

		// 각 캐시(Cache) 객체를 리스트로 생성
		List<CaffeineCache> caches = Arrays.asList(

			// 1. 게시판 리스트 (5분 유지, 최대 1000개)
			new CaffeineCache( //0
				CacheKeys.OnlineMbers.name(), //
				Caffeine.newBuilder() //
					.expireAfterWrite(5, TimeUnit.MINUTES) //
					.maximumSize(1000) //
					.build()),

			// 2. 오늘의 말씀 (24시간 유지, 최대 10개)
			new CaffeineCache( //
				CacheKeys.TodayBibleVerse.name(), //
				Caffeine.newBuilder() //
					.expireAfterWrite(24, TimeUnit.HOURS) //
					.maximumSize(10) //
					.build()),

			// 3. 맴버 달란트 (24시간 유지, 최대 10개)
			new CaffeineCache( //
				CacheKeys.MberPoint.name(), //
				Caffeine.newBuilder() //
					.expireAfterWrite(10, TimeUnit.MINUTES) //
					.maximumSize(1000) //
					.build()),

			// 4. 맴버 레벨 (10분 유지, 최대 1000개)
			new CaffeineCache( //
				CacheKeys.MberLv.name(), //
				Caffeine.newBuilder() //
					.expireAfterWrite(10, TimeUnit.MINUTES) //
					.maximumSize(1000) //
					.build()),

			// 5. 맴버 경험치 (10분 유지, 최대 1000개)
			new CaffeineCache( //
				CacheKeys.MberExp.name(), //
				Caffeine.newBuilder() //
					.expireAfterWrite(10, TimeUnit.MINUTES) //
					.maximumSize(1000) //
					.build()),

			// 6. 기본/기타 캐시 (60분 유지, 최대 100개)
			new CaffeineCache( //
				"defaultCache", //
				Caffeine.newBuilder() //
					.expireAfterWrite(60, TimeUnit.MINUTES) //
					.maximumSize(1000) //
					.build()));

		cacheManager.setCaches(caches);

		// 필요하다면 캐시 이름별로 다른 설정을 할 수도 있습니다.
		return cacheManager;
	}

}