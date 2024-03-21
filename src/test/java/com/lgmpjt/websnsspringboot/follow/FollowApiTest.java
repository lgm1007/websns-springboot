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
		Long fromFollow = memberCommandUseCase.createMember(requestMemberCreateDto("userId1", "1234", "David", "david@example.com"))
				.getMemberSeq();
		Long toFollow = memberCommandUseCase.createMember(requestMemberCreateDto("userId2", "5678", "John", "john@example.com"))
				.getMemberSeq();

		// API 요청
		ExtractableResponse<Response> response = requestDoFollow(fromFollow, toFollow);

		// 응답 값 검증
		AssertionsForClassTypes.assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
	}

	@Test
	void doUnfollow() {
		// 팔로우 수행, 팔로우 대상 유저 생성
		Long fromFollow = memberCommandUseCase.createMember(requestMemberCreateDto("userId1", "1234", "David", "david@example.com"))
				.getMemberSeq();
		Long toFollow = memberCommandUseCase.createMember(requestMemberCreateDto("userId2", "5678", "John", "john@example.com"))
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
		Long fromMemberSeq = memberCommandUseCase.createMember(requestMemberCreateDto("userId1", "1234", "David", "david@example.com"))
				.getMemberSeq();
		Long toFollow1 = memberCommandUseCase.createMember(requestMemberCreateDto("userId2", "5678", "John", "john@example.com"))
				.getMemberSeq();
		Long toFollow2 = memberCommandUseCase.createMember(requestMemberCreateDto("userId3", "9012", "Raychel", "raychel@example.com"))
				.getMemberSeq();

		// 팔로우 생성
		followCommandUseCase.saveFollow(fromMemberSeq, toFollow1);
		followCommandUseCase.saveFollow(fromMemberSeq, toFollow2);
		
		// 조회 API 요청
		ResponseBody body = requestSearchFollowing(fromMemberSeq);
		
		// API 응답값 검증
		AssertionsForClassTypes.assertThat(body.as(ArrayList.class).size()).isEqualTo(2);
	}

	@Test
	void doSearchFollower() {
		// 팔로우 수행, 팔로우 대상 유저 생성
		Long fromMemberSeq1 = memberCommandUseCase.createMember(requestMemberCreateDto("userId1", "1234", "David", "david@example.com"))
				.getMemberSeq();
		Long fromMemberSeq2 = memberCommandUseCase.createMember(requestMemberCreateDto("userId2", "5678", "John", "john@example.com"))
				.getMemberSeq();
		Long fromMemberSeq3 = memberCommandUseCase.createMember(requestMemberCreateDto("userId3", "9012", "Raychel", "raychel@example.com"))
				.getMemberSeq();
		Long toMemberSeq = memberCommandUseCase.createMember(requestMemberCreateDto("userId4", "1111", "Celeb", "celeb@example.com"))
				.getMemberSeq();

		// 팔로우 생성
		followCommandUseCase.saveFollow(fromMemberSeq1, toMemberSeq);
		followCommandUseCase.saveFollow(fromMemberSeq2, toMemberSeq);
		followCommandUseCase.saveFollow(fromMemberSeq3, toMemberSeq);

		// 조회 API 요청
		ResponseBody body = requestSearchFollower(toMemberSeq);

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

	private static ResponseBody requestSearchFollowing(Long fromMemberSeq) {
		return RestAssured.given().log().all()
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.when()
				.get("/api/follow/{memberSeq}/following", fromMemberSeq)
				.then()
				.log().all().extract().response()
				.getBody();
	}

	private static ResponseBody requestSearchFollower(Long toMemberSeq) {
		return RestAssured.given().log().all()
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.when()
				.get("/api/follow/{memberSeq}/follower", toMemberSeq)
				.then()
				.log().all().extract().response()
				.getBody();
	}

	private static MemberCreateDto requestMemberCreateDto(String memberId, String password, String memberName, String email) {
		boolean isAdmin = false;
		boolean isPrivate = false;
		return new MemberCreateDto(memberId, password, memberName, email, isAdmin, isPrivate);
	}
}
