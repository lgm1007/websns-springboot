package com.lgmpjt.websnsspringboot.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

	private static final String API_TITLE = "Web SNS API";
	private static final String API_VERSION = "1.0.0";
	private static final String API_DESCRIPTION = "WEB_SNS_SPRING_BOOT API 명세서입니다.";

	@Bean
	public OpenAPI openAPI() {
		return new OpenAPI()
				.components(new Components())
				.info(info());
	}

	private static Info info() {
		return new Info()
				.title(API_TITLE)
				.version(API_VERSION)
				.description(API_DESCRIPTION);
	}
}
