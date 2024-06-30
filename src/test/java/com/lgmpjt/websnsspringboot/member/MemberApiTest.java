package com.lgmpjt.websnsspringboot.member;

import com.lgmpjt.websnsspringboot.ApiTest;
import com.lgmpjt.websnsspringboot.adapter.out.persistence.entity.Member;
import com.lgmpjt.websnsspringboot.application.port.in.MemberCommandUseCase;
import com.lgmpjt.websnsspringboot.application.port.in.MemberSearchUseCase;
import com.lgmpjt.websnsspringboot.application.port.in.dto.MemberCreateDto;
import com.lgmpjt.websnsspringboot.application.port.in.dto.MemberDto;
import com.lgmpjt.websnsspringboot.application.port.in.enumeration.MemberGrant;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class MemberApiTest extends ApiTest {

	@Autowired
	private MemberCommandUseCase memberCommandUseCase;
	@Autowired
	private MemberSearchUseCase memberSearchUseCase;

	@Test
	void createMember() {
		final MemberCreateDto memberCreateDto = requestMemberCreateDto(
			"memberId1",
			"password",
			"memberName1",
			"memberId1@example.com"
		);

		// API 요청
		final ExtractableResponse<Response> response = requestMemberCreateApi(memberCreateDto);

		assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
	}

	@Test
	void searchMember() {
		// 유저 생성
		Member member = memberCommandUseCase.createMember(requestMemberCreateDto(
			"memberId2",
			"password",
			"memberName2",
			"memberId2@example.com"
		));
		final Long memberSeq = member.getMemberSeq();

		// 유저 조회
		ResponseBody body = requestFindMemberApi(memberSeq);

		// 조회 응답 검증
		assertThat(body.as(MemberDto.class).getMemberName()).isEqualTo(member.getMemberName());
	}

	@Test
	void searchMemberByMemberId() {
		// 유저 생성
		Member member = memberCommandUseCase.createMember(requestMemberCreateDto(
			"memberId3",
			"password",
			"memberName3",
			"memberId3@example.com"
		));
		final String memberId = member.getMemberId();

		// 유저 ID로 유저 조회
		MemberDto memberByMemberId = memberSearchUseCase.getMemberByMemberId(memberId);

		// 조회 검증
		assertThat(memberByMemberId.getMemberName()).isEqualTo(member.getMemberName());
	}

	@Test
	void isMemberGrant() {
		// 유저 생성
		Member member = memberCommandUseCase.createMember(requestMemberCreateDto(
			"memberId4",
			"password",
			"memberName4",
			"memberId4@example.com"
		));
		final Long memberSeq = member.getMemberSeq();

		// 유저 조회
		ResponseBody body = requestFindMemberApi(memberSeq);

		// 검증
		assertThat(body.as(MemberDto.class).getMemberGrant()).isEqualTo(MemberGrant.USER);
	}

	@Test
	void updateMember() {
		// 유저 생성
		Member member = memberCommandUseCase.createMember(requestMemberCreateDto(
			"memberId5",
			"password",
			"memberName5",
			"memberId5@example.com"
		));

		// 유저 조회
		final Long memberSeq = member.getMemberSeq();
		MemberDto memberDto = memberSearchUseCase.getMemberByMemberSeq(memberSeq);

		memberDto.setEmail("mytest1user@example.mail");
		memberDto.setMemberName("홍두께");

		// 유저 정보 업데이트
		ExtractableResponse<Response> response = requestUpdateMemberApi(memberSeq, memberDto);

		// 업데이트 응답 검증
		assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
	}

	@Test
	void withdrawMember() {
		// 유저 생성
		Member member = memberCommandUseCase.createMember(requestMemberCreateDto(
			"memberId6",
			"password",
			"memberName6",
			"memberId6@example.com"
		));

		final Long memberSeq = member.getMemberSeq();
		// 유저 회원탈퇴
		ExtractableResponse<Response> response = requestMemberWithdrawApi(memberSeq);
		
		// 유저 회원탈퇴 응답 검증
		assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
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

	private static MemberCreateDto requestMemberCreateDto(String memberId,
														  String password,
														  String memberName,
														  String email) {
		boolean isAdmin = false;
		boolean isPrivate = false;
		return MemberCreateDto.builder()
				.memberId(memberId)
				.password(password)
				.memberName(memberName)
				.email(email)
				.isAdmin(isAdmin)
				.isPrivate(isPrivate)
				.build();
	}

}
