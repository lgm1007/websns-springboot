package com.lgmpjt.websnsspringboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;

// 테스트 코드용 Security 설정 파일
// 테스트 코드를 실행할 때 호출하는 API를 모두 허용해주기 위한 설정
@Profile("test")
@Configuration
public class TestSecurityConfig {

	@Bean
	public SecurityFilterChain filterChainTest(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
				.csrf(CsrfConfigurer<HttpSecurity>::disable)
				.headers(headersConfig -> headersConfig.frameOptions(
								HeadersConfigurer.FrameOptionsConfig::disable
						)
				)
				.authorizeRequests(requests ->
						requests.requestMatchers("/**").permitAll()
								.anyRequest().permitAll()
				);

		return httpSecurity.build();
	}
}
