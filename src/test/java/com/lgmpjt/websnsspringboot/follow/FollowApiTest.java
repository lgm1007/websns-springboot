package com.lgmpjt.websnsspringboot.follow;

import com.lgmpjt.websnsspringboot.ApiTest;
import com.lgmpjt.websnsspringboot.application.port.in.FollowCommandUseCase;
import com.lgmpjt.websnsspringboot.application.port.in.MemberCommandUseCase;
import com.lgmpjt.websnsspringboot.application.port.in.dto.MemberCreateDto;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.ArrayList;

public class FollowApiTest extends ApiTest {
	@Autowired
	private FollowCommandUseCase followCommandUseCase;

	@Autowired
	private MemberCommandUseCase memberCommandUseCase;

	@Test
	void doFollow() {
		// 팔로우 수행, 팔로우 대상 유저 생성
		Long fromFollow = memberCommandUseCase.createMember(requestUserCreateDto("userId1", "1234", "David", "david@example.com"))
				.getMemberSeq();
		Long toFollow = memberCommandUseCase.createMember(requestUserCreateDto("userId2", "5678", "John", "john@example.com"))
				.getMemberSeq();

		// API 요청
		ExtractableResponse<Response> response = requestDoFollow(fromFollow, toFollow);

		// 응답 값 검증
		AssertionsForClassTypes.assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
	}

	@Test
	void doUnfollow() {
		// 팔로우 수행, 팔로우 대상 유저 생성
		Long fromFollow = memberCommandUseCase.createMember(requestUserCreateDto("userId1", "1234", "David", "david@example.com"))
				.getMemberSeq();
		Long toFollow = memberCommandUseCase.createMember(requestUserCreateDto("userId2", "5678", "John", "john@example.com"))
				.getMemberSeq();

		// 팔로우 생성
		followCommandUseCase.saveFollow(fromFollow, toFollow);
		
		// 언팔로우 API 요청
		ExtractableResponse<Response> response = requestDoUnfollow(fromFollow, toFollow);

		// 응답값 검증
		AssertionsForClassTypes.assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
	}

	@Test
	void doSearchFollowing() {
		// 팔로우 수행, 팔로우 대상 유저 생성
		Long fromUserSeq = memberCommandUseCase.createMember(requestUserCreateDto("userId1", "1234", "David", "david@example.com"))
				.getMemberSeq();
		Long toFollow1 = memberCommandUseCase.createMember(requestUserCreateDto("userId2", "5678", "John", "john@example.com"))
				.getMemberSeq();
		Long toFollow2 = memberCommandUseCase.createMember(requestUserCreateDto("userId3", "9012", "Raychel", "raychel@example.com"))
				.getMemberSeq();

		// 팔로우 생성
		followCommandUseCase.saveFollow(fromUserSeq, toFollow1);
		followCommandUseCase.saveFollow(fromUserSeq, toFollow2);
		
		// 조회 API 요청
		ResponseBody body = requestSearchFollowing(fromUserSeq);
		
		// API 응답값 검증
		AssertionsForClassTypes.assertThat(body.as(ArrayList.class).size()).isEqualTo(2);
	}

	@Test
	void doSearchFollower() {
		// 팔로우 수행, 팔로우 대상 유저 생성
		Long fromUserSeq1 = memberCommandUseCase.createMember(requestUserCreateDto("userId1", "1234", "David", "david@example.com"))
				.getMemberSeq();
		Long fromUserSeq2 = memberCommandUseCase.createMember(requestUserCreateDto("userId2", "5678", "John", "john@example.com"))
				.getMemberSeq();
		Long fromUserSeq3 = memberCommandUseCase.createMember(requestUserCreateDto("userId3", "9012", "Raychel", "raychel@example.com"))
				.getMemberSeq();
		Long toUserSeq = memberCommandUseCase.createMember(requestUserCreateDto("userId4", "1111", "Celeb", "celeb@example.com"))
				.getMemberSeq();

		// 팔로우 생성
		followCommandUseCase.saveFollow(fromUserSeq1, toUserSeq);
		followCommandUseCase.saveFollow(fromUserSeq2, toUserSeq);
		followCommandUseCase.saveFollow(fromUserSeq3, toUserSeq);

		// 조회 API 요청
		ResponseBody body = requestSearchFollower(toUserSeq);

		// API 응답값 검증
		AssertionsForClassTypes.assertThat(body.as(ArrayList.class).size()).isEqualTo(3);
	}

	private static ExtractableResponse<Response> requestDoFollow(Long fromFollow, Long toFollow) {
		return RestAssured.given().log().all()
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.when()
				.post("/api/follow/{fromFollow}/to/{toFollow}", fromFollow, toFollow)
				.then()
				.log().all().extract();
	}

	private static ExtractableResponse<Response> requestDoUnfollow(Long fromFollow, Long toFollow) {
		return RestAssured.given().log().all()
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.when()
				.delete("/api/follow/{fromFollow}/to/{toFollow}", fromFollow, toFollow)
				.then()
				.log().all().extract();
	}

	private static ResponseBody requestSearchFollowing(Long fromUserSeq) {
		return RestAssured.given().log().all()
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.when()
				.get("/api/follow/{userSeq}/following", fromUserSeq)
				.then()
				.log().all().extract().response()
				.getBody();
	}

	private static ResponseBody requestSearchFollower(Long toUserSeq) {
		return RestAssured.given().log().all()
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.when()
				.get("/api/follow/{userSeq}/follower", toUserSeq)
				.then()
				.log().all().extract().response()
				.getBody();
	}

	private static MemberCreateDto requestUserCreateDto(String userId, String password, String userName, String userEmail) {
		boolean isAdmin = false;
		boolean isPrivate = false;
		return new MemberCreateDto(userId, password, userName, userEmail, isAdmin, isPrivate);
	}
}
