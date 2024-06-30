package com.lgmpjt.websnsspringboot.like;

import com.lgmpjt.websnsspringboot.ApiTest;
import com.lgmpjt.websnsspringboot.adapter.in.rest.request.BoardCreateRequest;
import com.lgmpjt.websnsspringboot.application.port.in.BoardCommandUseCase;
import com.lgmpjt.websnsspringboot.application.port.in.LikeCommandUseCase;
import com.lgmpjt.websnsspringboot.application.port.in.MemberCommandUseCase;
import com.lgmpjt.websnsspringboot.application.port.in.dto.MemberCreateDto;
import com.lgmpjt.websnsspringboot.application.port.in.dto.MemberDto;
import com.lgmpjt.websnsspringboot.mapper.MemberMapper;
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

public class LikeEntityApiTest extends ApiTest {

	@Autowired
	private MemberCommandUseCase memberCommandUseCase;

	@Autowired
	private BoardCommandUseCase boardCommandUseCase;

	@Autowired
	private LikeCommandUseCase likeCommandUseCase;

	@Test
	void doLike() {
		// 유저 생성
		MemberDto memberDto = MemberMapper.INSTANCE.toMemberDto(
				memberCommandUseCase.createMember(createMemberCreateDto("memberId1", "1234", "David", "david@example.com"))
		);

		Long memberSeq = memberDto.getMemberSeq();

		// 게시물 생성
		Long boardSeq = boardCommandUseCase.createBoard(createBoardCreateRequest(memberDto.getMemberSeq()))
				.getBoardSeq();

		// 게시물 좋아요하기
		ExtractableResponse<Response> response = requestDoLike(memberSeq, boardSeq);

		// 응답값 검증
		AssertionsForClassTypes.assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
	}
	
	@Test
	void undoLike() {
		// 유저 생성
		MemberDto memberDto = MemberMapper.INSTANCE.toMemberDto(
				memberCommandUseCase.createMember(createMemberCreateDto("memberId2", "1234", "White", "white@example.com"))
		);

		Long memberSeq = memberDto.getMemberSeq();

		// 게시물 생성
		Long boardSeq = boardCommandUseCase.createBoard(createBoardCreateRequest(memberDto.getMemberSeq()))
				.getBoardSeq();

		
		// 좋아요 하기
		likeCommandUseCase.createLike(memberSeq, boardSeq);

		// 좋아요 취소하기
		ExtractableResponse<Response> response = requestUndoLike(memberSeq, boardSeq);

		// 응답값 검증
		AssertionsForClassTypes.assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
	}

	@Test
	void getLikeListByUser() {
		// 유저 생성
		MemberDto memberDto = MemberMapper.INSTANCE.toMemberDto(
				memberCommandUseCase.createMember(createMemberCreateDto("memberId3", "1234", "Adam", "adam@example.com"))
		);

		Long memberSeq = memberDto.getMemberSeq();

		// 게시물 생성
		Long boardSeq = boardCommandUseCase.createBoard(createBoardCreateRequest(memberDto.getMemberSeq()))
				.getBoardSeq();

		// 좋아요 하기
		likeCommandUseCase.createLike(memberSeq, boardSeq);

		// 특정 유저가 좋아요 한 게시글 리스트 조회하기 요청
		ResponseBody body = requestLikeListByMember(memberSeq);

		// 응답값 검증
		AssertionsForClassTypes.assertThat(body.as(ArrayList.class).size()).isEqualTo(1);
	}

	private static ExtractableResponse<Response> requestDoLike(final Long userSeq, final Long boardSeq) {
		return RestAssured.given().log().all()
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.when()
				.post("/api/like/{userSeq}/to/{boardSeq}", userSeq, boardSeq)
				.then()
				.log().all().extract();
	}

	private static ExtractableResponse<Response> requestUndoLike(final Long userSeq, final Long boardSeq) {
		return RestAssured.given().log().all()
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.when()
				.delete("/api/like/undo/{userSeq}/to/{boardSeq}", userSeq, boardSeq)
				.then()
				.log().all().extract();
	}

	private static ResponseBody requestLikeListByMember(final Long userSeq) {
		return RestAssured.given().log().all()
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.when()
				.get("/api/like/list/{userSeq}", userSeq)
				.then()
				.log().all().extract().response()
				.body();
	}

	private static MemberCreateDto createMemberCreateDto(String memberId, String password, String memberName, String memberEmail) {
		boolean isAdmin = false;
		boolean isPrivate = false;
		return MemberCreateDto.builder()
				.memberId(memberId)
				.password(password)
				.memberName(memberName)
				.email(memberEmail)
				.isAdmin(isAdmin)
				.isPrivate(isPrivate)
				.build();
	}

	private static BoardCreateRequest createBoardCreateRequest(Long memberSeq) {
		String content = "새로운 게시물입니다.";
		String boardImage = "images/img01.jpg";
		return BoardCreateRequest.of(content, boardImage, memberSeq);
	}
}
