package com.lgmpjt.websnsspringboot.follow;

import com.lgmpjt.websnsspringboot.ApiTest;
import com.lgmpjt.websnsspringboot.domain.follow.service.FollowService;
import com.lgmpjt.websnsspringboot.domain.user.data.UserCreateDto;
import com.lgmpjt.websnsspringboot.domain.user.service.UserService;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

public class FollowApiTest extends ApiTest {
	@Autowired
	private FollowService followService;

	@Autowired
	private UserService userService;

	@Test
	void doFollow() {
		// 팔로우 수행, 팔로우 대상 유저 생성
		Long fromFollow = userService.createUser(requestUserCreateDto("userId1", "1234", "David", "david@example.com"))
				.getUserSeq();
		Long toFollow = userService.createUser(requestUserCreateDto("userId2", "5678", "John", "john@example.com"))
				.getUserSeq();

		// API 요청
		ExtractableResponse<Response> response = requestDoFollow(fromFollow, toFollow);

		// 응답 값 검증
		AssertionsForClassTypes.assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
	}

	private static ExtractableResponse<Response> requestDoFollow(Long fromFollow, Long toFollow) {
		return RestAssured.given().log().all()
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.when()
				.post("/follows/{fromFollow}/to/{toFollow}", fromFollow, toFollow)
				.then()
				.log().all().extract();
	}

	private static UserCreateDto requestUserCreateDto(String userId, String password, String userName, String userEmail) {
		return new UserCreateDto(userId, password, userName, userEmail, false);
	}
}
