package com.lgmpjt.websnsspringboot.follow;

import com.lgmpjt.websnsspringboot.ApiTest;
import com.lgmpjt.websnsspringboot.adapter.in.rest.request.MemberCreateRequest;
import com.lgmpjt.websnsspringboot.application.port.in.FollowCommandUseCase;
import com.lgmpjt.websnsspringboot.application.port.in.MemberCommandUseCase;
import com.lgmpjt.websnsspringboot.application.port.service.dto.MemberServiceDto;
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
		final MemberCreateRequest memberCreateRequest1 = requestMemberCreateDto("memberId1", "1234", "David", "david@example.com");
		final MemberCreateRequest memberCreateRequest2 = requestMemberCreateDto("memberId2", "5678", "John", "john@example.com");
		final Long fromFollow = memberCommandUseCase.createMember(MemberServiceDto.from(memberCreateRequest1)).getMemberSeq();
		final Long toFollow = memberCommandUseCase.createMember(MemberServiceDto.from(memberCreateRequest2)).getMemberSeq();

		// API 요청
		ExtractableResponse<Response> response = requestDoFollow(fromFollow, toFollow);

		// 응답 값 검증
		AssertionsForClassTypes.assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
	}

	@Test
	void doUnfollow() {
		// 팔로우 수행, 팔로우 대상 유저 생성
		final MemberCreateRequest memberCreateRequest1 = requestMemberCreateDto("memberId3", "1234", "White", "white@example.com");
		final MemberCreateRequest memberCreateRequest2 = requestMemberCreateDto("memberId4", "5678", "Paul", "paul@example.com");
		final Long fromFollow = memberCommandUseCase.createMember(MemberServiceDto.from(memberCreateRequest1)).getMemberSeq();
		final Long toFollow = memberCommandUseCase.createMember(MemberServiceDto.from(memberCreateRequest2)).getMemberSeq();

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
		final MemberCreateRequest memberCreateRequest1 = requestMemberCreateDto("memberId5", "1234", "Tom", "tom@example.com");
		final MemberCreateRequest memberCreateRequest2 = requestMemberCreateDto("memberId6", "5678", "Grace", "grace@example.com");
		final MemberCreateRequest memberCreateRequest3 = requestMemberCreateDto("memberId7", "9012", "Raychel", "raychel@example.com");
		final Long fromMemberSeq = memberCommandUseCase.createMember(MemberServiceDto.from(memberCreateRequest1)).getMemberSeq();
		final Long toFollow1 = memberCommandUseCase.createMember(MemberServiceDto.from(memberCreateRequest2)).getMemberSeq();
		final Long toFollow2 = memberCommandUseCase.createMember(MemberServiceDto.from(memberCreateRequest3)).getMemberSeq();

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
		final MemberCreateRequest memberCreateRequest1 = requestMemberCreateDto("memberId8", "1234", "Tomas", "tomas@example.com");
		final MemberCreateRequest memberCreateRequest2 = requestMemberCreateDto("memberId9", "5678", "Jake", "jake@example.com");
		final MemberCreateRequest memberCreateRequest3 = requestMemberCreateDto("memberId10", "9012", "Ulice", "ulice@example.com");
		final MemberCreateRequest memberCreateRequest4 = requestMemberCreateDto("memberId11", "1111", "Celeb", "celeb@example.com");

		final Long fromMemberSeq1 = memberCommandUseCase.createMember(MemberServiceDto.from(memberCreateRequest1)).getMemberSeq();
		final Long fromMemberSeq2 = memberCommandUseCase.createMember(MemberServiceDto.from(memberCreateRequest2)).getMemberSeq();
		final Long fromMemberSeq3 = memberCommandUseCase.createMember(MemberServiceDto.from(memberCreateRequest3)).getMemberSeq();
		final Long toMemberSeq = memberCommandUseCase.createMember(MemberServiceDto.from(memberCreateRequest4)).getMemberSeq();

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
				.get("/api/follow/following/{memberSeq}", fromMemberSeq)
				.then()
				.log().all().extract().response()
				.getBody();
	}

	private static ResponseBody requestSearchFollower(Long toMemberSeq) {
		return RestAssured.given().log().all()
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.when()
				.get("/api/follow/follower/{memberSeq}", toMemberSeq)
				.then()
				.log().all().extract().response()
				.getBody();
	}

	private static MemberCreateRequest requestMemberCreateDto(String memberId, String password, String memberName, String email) {
		boolean isAdmin = false;
		boolean isPrivate = false;
		return MemberCreateRequest.builder()
				.memberId(memberId)
				.password(password)
				.memberName(memberName)
				.email(email)
				.isAdmin(isAdmin)
				.isPrivate(isPrivate)
				.build();
	}
}
