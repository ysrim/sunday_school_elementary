package com.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@EnableCaching // 캐시 기능 활성화
@Configuration
public class CacheConfig {

	@Bean
	public CacheManager cacheManager() {

		CaffeineCacheManager cacheManager = new CaffeineCacheManager();

		// 기본 설정: 작성 후 30분 뒤 만료, 최대 1000개 저장
		cacheManager.setCaffeine(Caffeine.newBuilder()
				.expireAfterWrite(60, TimeUnit.MINUTES)
				.maximumSize(1000));

		// 필요하다면 캐시 이름별로 다른 설정을 할 수도 있습니다.
		return cacheManager;
	}
}