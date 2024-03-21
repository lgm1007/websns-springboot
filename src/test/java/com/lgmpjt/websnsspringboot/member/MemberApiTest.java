package com.lgmpjt.websnsspringboot.member;

import com.lgmpjt.websnsspringboot.ApiTest;
import com.lgmpjt.websnsspringboot.application.port.in.MemberCommandUseCase;
import com.lgmpjt.websnsspringboot.application.port.in.MemberSearchUseCase;
import com.lgmpjt.websnsspringboot.application.port.in.dto.MemberCreateDto;
import com.lgmpjt.websnsspringboot.application.port.in.dto.MemberDto;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

class MemberApiTest extends ApiTest {

	@Autowired
	private MemberCommandUseCase memberCommandUseCase;
	private MemberSearchUseCase memberSearchUseCase;

	@Test
	void createMember() {
		final MemberCreateDto memberCreateDto = requestMemberCreateDto();

		// API 요청
		final ExtractableResponse<Response> response = requestMemberCreateApi(memberCreateDto);

		AssertionsForClassTypes.assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
	}

	@Test
	void searchMember() {
		// 유저 생성
		memberCommandUseCase.createMember(requestMemberCreateDto());
		final Long memberSeq = 1L;

		// 유저 조회
		ResponseBody body = requestFindMemberApi(memberSeq);

		// 조회 응답 검증
		AssertionsForClassTypes.assertThat(body.as(MemberDto.class).getMemberName()).isEqualTo("홍길동");
	}

	@Test
	void updateMember() {
		// 유저 생성
		memberCommandUseCase.createMember(requestMemberCreateDto());

		// 유저 조회
		final Long memberSeq = 1L;
		MemberDto memberDto = memberSearchUseCase.getMemberByMemberSeq(memberSeq);

		memberDto.setEmail("mytest1user@example.mail");
		memberDto.setMemberName("홍두께");

		// 유저 정보 업데이트
		ExtractableResponse<Response> response = requestUpdateMemberApi(memberSeq, memberDto);

		// 업데이트 응답 검증
		AssertionsForClassTypes.assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
	}

	@Test
	void withdrawMember() {
		// 유저 생성
		memberCommandUseCase.createMember(requestMemberCreateDto());

		final Long memberSeq = 1L;
		// 유저 회원탈퇴
		ExtractableResponse<Response> response = requestMemberWithdrawApi(memberSeq);
		
		// 유저 회원탈퇴 응답 검증
		AssertionsForClassTypes.assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
	}



	private static ExtractableResponse<Response> requestMemberCreateApi(MemberCreateDto memberCreateDto) {
		return RestAssured.given().log().all()
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.body(memberCreateDto)
				.when()
				.post("/api/member")
				.then()
				.log().all().extract();
	}

	private static ResponseBody requestFindMemberApi(Long memberSeq) {
		return RestAssured.given().log().all()
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.when()
				.get("/api/member/{memberSeq}", memberSeq)
				.then()
				.log().all().extract().response()
				.getBody();
	}

	private static ExtractableResponse<Response> requestUpdateMemberApi(Long memberSeq, MemberDto memberDto) {
		return RestAssured.given().log().all()
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.body(memberDto)
				.when()
				.put("/api/member/{memberSeq}", memberSeq)
				.then()
				.log().all().extract();
	}

	private static ExtractableResponse<Response> requestMemberWithdrawApi(Long memberSeq) {
		return RestAssured.given().log().all()
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.when()
				.delete("/api/member/{memberSeq}", memberSeq)
				.then()
				.log().all().extract();
	}

	private static MemberCreateDto requestMemberCreateDto() {
		String memberId = "memberId";
		String password = "password";
		String memberName = "홍길동";
		String email = "mysns@example.com";
		boolean isAdmin = false;
		boolean isPrivate = false;
		return new MemberCreateDto(memberId, password, memberName, email, isAdmin, isPrivate);
	}

}
