package com.lgmpjt.websnsspringboot.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	private final String[] allowedUrls = {"/login", "/signup", "/swagger-ui/**"};		// /login, /signup, swagger-ui 페이지는 항상 허용

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

		httpSecurity
				.csrf(CsrfConfigurer<HttpSecurity>::disable)
				.authorizeRequests(requests ->
						requests.requestMatchers(allowedUrls).permitAll()
								.requestMatchers(toH2Console()).permitAll()		// H2 console 페이지 접근도 항상 허용
								.anyRequest().authenticated()
				)
				.sessionManagement(sessionManagementConfigurer ->
						sessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS)  // 세션 사용 안하므로 STATELESS로 설정
				);


		return httpSecurity.build();
	}
}
