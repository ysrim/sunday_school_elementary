package com.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	// 1. 암호화 모듈 빈 등록 (BCrypt 강력 추천)
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// 2. 보안 필터 체인 설정 (Spring Security 6.1 Lambda DSL 방식)
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			// 경로별 권한 설정
			.authorizeHttpRequests(auth -> auth
				.requestMatchers("/login", "/join", "/css/**", "/js/**").permitAll() // 로그인, 회원가입, 리소스는 모두 허용
				.anyRequest().authenticated() // 그 외는 인증 필요
			)
			// 로그인 설정
			.formLogin(login -> login
				.loginPage("/login")        // 커스텀 로그인 페이지 경로
				.loginProcessingUrl("/loginProc") // form action 경로
				.defaultSuccessUrl("/")     // 성공 시 이동 경로
				.permitAll()
			)
			// 로그아웃 설정
			.logout(logout -> logout
				.logoutUrl("/logout")
				.logoutSuccessUrl("/")
			);

		return http.build();
	}
}