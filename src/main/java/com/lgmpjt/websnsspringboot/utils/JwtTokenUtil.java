package com.lgmpjt.websnsspringboot.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenUtil {
	 // JWT Token 발급
	public static String createToken(String loginId, String key, long expireTimeMs) {
		Claims claims = Jwts.claims();		// Claims: JWT Token에 들어갈 정보
		claims.put("loginId", loginId);		// JWT Token에 loginId 추가해 줌

		return Jwts.builder()
				.setClaims(claims)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + expireTimeMs))
				.signWith(SignatureAlgorithm.HS256, key)
				.compact();
	}

	// Claims 정보에서 loginId 추출
	public static String getLoginIdInClaims(String token, String key) {
		return extractClaims(token, key)
				.get("loginId")
				.toString();
	}

	// 발급된 토큰이 만료 시간 지났는지 확인
	public static boolean isExpired(String token, String key) {
		Date expiredDate = extractClaims(token, key).getExpiration();
		return expiredDate.before(new Date(System.currentTimeMillis()));
	}

	// JWT Token 복호화하여 Claims 정보 추출
	private static Claims extractClaims(String token, String key) {
		try {
			return Jwts.parserBuilder()
					.setSigningKey(key)
					.build()
					.parseClaimsJws(token)
					.getBody();
		} catch (ExpiredJwtException e) {
			return e.getClaims();
		}
	}
}
