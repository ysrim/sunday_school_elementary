package com.config.context;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableWebSecurity
@PropertySource("classpath:spring/prop/globals.properties")
public class SecurityConfig {

	@Value("${globals.service.mode}")
	private String serviceMode;

	@Bean
	public PasswordEncoder passwordEncoder() {

		return new BCryptPasswordEncoder();

	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		if ("real".equals(serviceMode)) { // 운영일 때는 https 강제 적용
			log.info("serviceMode: {}", serviceMode);
			http.requiresChannel(channel -> channel.anyRequest().requiresSecure());
		}

		http.authorizeHttpRequests(auth -> auth.anyRequest().permitAll());

		return http.build();
	}

}