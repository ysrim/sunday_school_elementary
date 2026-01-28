package com.config.context;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
public class SchedulerConfig {
	// 필요 시 ThreadPoolTaskScheduler 설정 등을 여기서 커스터마이징 가능
}