package com.lgmpjt.websnsspringboot.utils;

import com.lgmpjt.websnsspringboot.application.port.in.MemberSearchUseCase;
import com.lgmpjt.websnsspringboot.application.port.in.dto.MemberDto;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {	// OncePerRequestFilter: 매 요청때마다 체크해주는 필터

	private final String secretKey;
	private final MemberSearchUseCase memberSearchUseCase;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		String token = request.getHeader(HttpHeaders.AUTHORIZATION);

		// header 내 AUTHORIZATION 값(token)이 비어있다면 JWT 토큰 전송 안 하고, 로그인하지 않음
		if (token == null) {
			filterChain.doFilter(request, response);
			return;
		}

		// 받아온 Jwt 토큰이 만료되었다면 인증 안 함
		if (JwtTokenUtil.isExpired(token, secretKey)) {
			filterChain.doFilter(request, response);
			return;
		}

		String loginId = JwtTokenUtil.getLoginIdInClaims(token, secretKey);
		// 추출한 loginId로 멤버 정보 조회해오기
		MemberDto memberDto = memberSearchUseCase.getMemberByMemberId(loginId);

		// member 정보로 UserAuthenticationToken 발급
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				memberDto.getMemberId(),
				null,
				List.of(new SimpleGrantedAuthority(memberDto.getMemberGrant().name()))
		);
		authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

		// SecurityContextHolder에 권한 부여
		SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		filterChain.doFilter(request, response);
	}
}
