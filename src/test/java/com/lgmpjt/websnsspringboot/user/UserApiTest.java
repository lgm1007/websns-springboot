package com.lgmpjt.websnsspringboot.user;

import com.lgmpjt.websnsspringboot.ApiTest;
import com.lgmpjt.websnsspringboot.application.port.in.UserCommandUseCase;
import com.lgmpjt.websnsspringboot.application.port.in.UserSearchUseCase;
import com.lgmpjt.websnsspringboot.application.port.in.dto.UserCreateDto;
import com.lgmpjt.websnsspringboot.application.port.in.dto.UserDto;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

class UserApiTest extends ApiTest {

	@Autowired
	private UserCommandUseCase userCommandUseCase;
	private UserSearchUseCase userSearchUseCase;

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
		userCommandUseCase.createUser(requestUserCreateDto());
		final Long userSeq = 1L;

		// 유저 조회
		ResponseBody body = requestFindUserApi(userSeq);

		// 조회 응답 검증
		AssertionsForClassTypes.assertThat(body.as(UserDto.class).getUserName()).isEqualTo("홍길동");
	}

	@Test
	void updateUser() {
		// 유저 생성
		userCommandUseCase.createUser(requestUserCreateDto());

		// 유저 조회
		final Long userSeq = 1L;
		UserDto userDto = userSearchUseCase.getUserByUserSeq(userSeq);

		userDto.setUserEmail("mytest1user@example.mail");
		userDto.setUserName("홍두께");

		// 유저 정보 업데이트
		ExtractableResponse<Response> response = requestUpdateUserApi(userSeq, userDto);

		// 업데이트 응답 검증
		AssertionsForClassTypes.assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
	}

	@Test
	void withdrawUser() {
		// 유저 생성
		userCommandUseCase.createUser(requestUserCreateDto());

		final Long userSeq = 1L;
		// 유저 회원탈퇴
		ExtractableResponse<Response> response = requestUserWithdrawApi(userSeq);
		
		// 유저 회원탈퇴 응답 검증
		AssertionsForClassTypes.assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
	}



	private static ExtractableResponse<Response> requestUserCreateApi(UserCreateDto userCreateDto) {
		return RestAssured.given().log().all()
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.body(userCreateDto)
				.when()
				.post("/api/user")
				.then()
				.log().all().extract();
	}

	private static ResponseBody requestFindUserApi(Long userSeq) {
		return RestAssured.given().log().all()
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.when()
				.get("/api/user/{userSeq}", userSeq)
				.then()
				.log().all().extract().response()
				.getBody();
	}

	private static ExtractableResponse<Response> requestUpdateUserApi(Long userSeq, UserDto userDto) {
		return RestAssured.given().log().all()
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.body(userDto)
				.when()
				.put("/api/user/{userSeq}", userSeq)
				.then()
				.log().all().extract();
	}

	private static ExtractableResponse<Response> requestUserWithdrawApi(Long userSeq) {
		return RestAssured.given().log().all()
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.when()
				.delete("/api/user/{userSeq}", userSeq)
				.then()
				.log().all().extract();
	}

	private static UserCreateDto requestUserCreateDto() {
		String userId = "userId";
		String password = "password";
		String userName = "홍길동";
		String userEmail = "mysns@example.com";
		boolean isAdmin = false;
		boolean isPrivate = false;
		return new UserCreateDto(userId, password, userName, userEmail, isAdmin, isPrivate);
	}

}
