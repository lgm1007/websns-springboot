package com.lgmpjt.websnsspringboot.user;

import com.lgmpjt.websnsspringboot.ApiTest;
import com.lgmpjt.websnsspringboot.user.data.UserCreateDto;
import com.lgmpjt.websnsspringboot.user.data.UserSearchUpdateDto;
import com.lgmpjt.websnsspringboot.user.service.UserService;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

class UserApiTest extends ApiTest {

	@Autowired
	private UserService userService;

	@Test
	void createUser() {
		final UserCreateDto userCreateDto = requestUserCreateDto();

		// API 요청
		final ExtractableResponse<Response> response = requestUserCreateApi(userCreateDto);

		AssertionsForClassTypes.assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
	}

	@Test
	void searchUser() {
		// 유저 생성
		userService.createUser(requestUserCreateDto());
		final Long userSeq = 1L;

		// 유저 조회
		ExtractableResponse<Response> response = requestFindUserApi(userSeq);

		// 조회 응답 검증
		AssertionsForClassTypes.assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
	}

	@Test
	void updateUser() {
		// 유저 생성
		userService.createUser(requestUserCreateDto());

		// 유저 조회
		final Long userSeq = 1L;
		UserSearchUpdateDto userDto = userService.findUser(userSeq);

		userDto.setUserEmail("mytest1user@example.mail");
		userDto.setUserName("홍두께");

		// 유저 정보 업데이트
		ExtractableResponse<Response> response = requestUpdateUserApi(userSeq, userDto);

		// 업데이트 응답 검증
		AssertionsForClassTypes.assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
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

	private static ExtractableResponse<Response> requestFindUserApi(Long userSeq) {
		return RestAssured.given().log().all()
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.when()
				.get("/users/{userSeq}", userSeq)
				.then()
				.log().all().extract();
	}

	private static ExtractableResponse<Response> requestUpdateUserApi(Long userSeq, UserSearchUpdateDto userDto) {
		return RestAssured.given().log().all()
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.body(userDto)
				.when()
				.put("/users/{userSeq}", userSeq)
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
