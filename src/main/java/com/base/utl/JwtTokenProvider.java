package com.base.utl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtTokenProvider {

	private @Value("#{globalsProps['secretKey.key.jwt']}") String secretKey;

	private final long accessTokenValidTime = 30 * 60 * 1000L; // 30분
	private final long refreshTokenValidTime = 1L * 365 * 24 * 60 * 60 * 1000; // 1년

	// 토큰 생성 공통 로직
	public String createToken(String userId, long validTime) {
		Claims claims = Jwts.claims().setSubject(userId);
		Date now = new Date();
		return Jwts.builder().setClaims(claims).setIssuedAt(now).setExpiration(new Date(now.getTime() + validTime)).signWith(SignatureAlgorithm.HS256, secretKey.getBytes()).compact();
	}

	// Access Token 생성
	public String createAccessToken(String userId) {
		return createToken(userId, accessTokenValidTime);
	}

	// Refresh Token 생성
	public String createRefreshToken(String userId) {
		return createToken(userId, refreshTokenValidTime);
	}

	// 토큰에서 회원 아이디 추출
	public String getUserId(String token) {
		return Jwts.parserBuilder().setSigningKey(secretKey.getBytes()).build().parseClaimsJws(token).getBody().getSubject();
	}

	// 토큰 유효성 확인
	public boolean validateToken(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(secretKey.getBytes()).build().parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}