package com.lgmpjt.websnsspringboot.user;

import com.lgmpjt.websnsspringboot.ApiTest;
import com.lgmpjt.websnsspringboot.user.data.UserCreateDto;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

class UserApiTest extends ApiTest {

	@Test
	void createUser() {
		final UserCreateDto userCreateDto = requestUserCreateDto();

		// API 요청
		final ExtractableResponse<Response> response = requestUserCreateApi(userCreateDto);

		AssertionsForClassTypes.assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
	}

	private static ExtractableResponse<Response> requestUserCreateApi(UserCreateDto userCreateDto) {
		return RestAssured.given().log().all()
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.body(userCreateDto)
				.when()
				.post("/users")
				.then()
				.log().all().extract();
	}

	private static UserCreateDto requestUserCreateDto() {
		String userId = "userId";
		String password = "password";
		String userName = "홍길동";
		String userEmail = "mysns@example.com";
		return new UserCreateDto(userId, password, userName, userEmail);
	}

}
