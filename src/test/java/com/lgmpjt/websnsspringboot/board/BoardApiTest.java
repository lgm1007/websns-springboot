package com.lgmpjt.websnsspringboot.board;

import com.lgmpjt.websnsspringboot.ApiTest;
import com.lgmpjt.websnsspringboot.adapter.in.rest.request.BoardCreateRequest;
import com.lgmpjt.websnsspringboot.adapter.in.rest.request.MemberCreateRequest;
import com.lgmpjt.websnsspringboot.adapter.out.persistence.entity.Member;
import com.lgmpjt.websnsspringboot.application.port.in.BoardCommandUseCase;
import com.lgmpjt.websnsspringboot.application.port.in.MemberCommandUseCase;
import com.lgmpjt.websnsspringboot.application.port.in.dto.BoardDto;
import com.lgmpjt.websnsspringboot.application.port.in.dto.MemberDto;
import com.lgmpjt.websnsspringboot.application.port.service.dto.BoardServiceDto;
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

import java.time.LocalDateTime;
import java.util.ArrayList;

public class BoardApiTest extends ApiTest {

	@Autowired
	private MemberCommandUseCase memberCommandUseCase;

	@Autowired
	private BoardCommandUseCase boardCommandUseCase;

	@Test
	void createBoard() {
		// 유저 생성
		final MemberCreateRequest memberCreateRequest = createMemberCreateRequest("memberId1", "1234", "David", "david@example.com");
		final Member member = memberCommandUseCase.createMember(MemberServiceDto.from(memberCreateRequest));
		final MemberDto memberDto = MemberDto.from(member);

		// 게시물 생성
		BoardCreateRequest boardCreateRequest = createBoardCreateRequest(memberDto.getMemberSeq());

		// 게시물 업로드 API 요청
		ExtractableResponse<Response> response = requestBoardCreateApi(memberDto.getMemberSeq(), boardCreateRequest);

		// 업로드 응답 검증
		AssertionsForClassTypes.assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
	}

	@Test
	void searchBoard() {
		// 유저 생성
		final MemberCreateRequest memberCreateRequest = createMemberCreateRequest("memberId2", "1234", "Tom", "tom@example.com");
		final Member member = memberCommandUseCase.createMember(MemberServiceDto.from(memberCreateRequest));
		final MemberDto memberDto = MemberDto.from(member);

		// 게시물 생성
		final BoardCreateRequest boardCreateRequest = createBoardCreateRequest(memberDto.getMemberSeq());
		final Long boardSeq = boardCommandUseCase.createBoard(BoardServiceDto.from(boardCreateRequest)).getBoardSeq();

		// 게시물 조회 API 요청
		ResponseBody body = requestSearchBoardApi(boardSeq);

		// 조회 응답 검증
		AssertionsForClassTypes.assertThat(body.as(BoardDto.class).getBoardSeq()).isEqualTo(boardSeq);
	}
	
	@Test
	void searchBoardsByMemberSeq() {
		// 유저 생성
		final MemberCreateRequest memberCreateRequest = createMemberCreateRequest("memberId3", "1234", "White", "white@example.com");
		final Member member = memberCommandUseCase.createMember(MemberServiceDto.from(memberCreateRequest));
		final MemberDto memberDto = MemberDto.from(member);

		// 게시물 생성
		final BoardCreateRequest boardCreateRequest = createBoardCreateRequest(memberDto.getMemberSeq());
		boardCommandUseCase.createBoard(BoardServiceDto.from(boardCreateRequest));

		ResponseBody body = requestSearchBoardsByMemberApi(memberDto.getMemberSeq());

		// 조회 응답 검증
		AssertionsForClassTypes.assertThat(body.as(ArrayList.class).size()).isEqualTo(1);
	}

	@Test
	void updateBoard() {
		// 유저 생성
		final MemberCreateRequest memberCreateRequest = createMemberCreateRequest("memberId4", "1234", "Grace", "grace@example.com");
		final Member member = memberCommandUseCase.createMember(MemberServiceDto.from(memberCreateRequest));
		final MemberDto memberDto = MemberDto.from(member);

		// 게시물 생성
		final Long memberSeq = memberDto.getMemberSeq();
		final BoardCreateRequest boardCreateRequest = createBoardCreateRequest(memberSeq);
		final Long boardSeq = boardCommandUseCase.createBoard(BoardServiceDto.from(boardCreateRequest)).getBoardSeq();

		// 업데이트 내용 포함된 BoardDto 생성
		BoardDto boardDto = createBoardDto(boardSeq, memberSeq, LocalDateTime.now());

		// 업데이트 API 요청
		ExtractableResponse<Response> response = requestUpdateBoard(boardSeq, boardDto);

		// 업데이트 응답 검증
		AssertionsForClassTypes.assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
	}

	@Test
	void deleteBoard() {
		// 유저 생성
		final MemberCreateRequest memberCreateRequest = createMemberCreateRequest("memberId5", "1234", "Paul", "paul@example.com");
		final Member member = memberCommandUseCase.createMember(MemberServiceDto.from(memberCreateRequest));
		final MemberDto memberDto = MemberDto.from(member);

		// 게시물 생성
		final BoardCreateRequest boardCreateRequest = createBoardCreateRequest(memberDto.getMemberSeq());
		final Long boardSeq = boardCommandUseCase.createBoard(BoardServiceDto.from(boardCreateRequest)).getBoardSeq();

		// 게시물 삭제 API 요청
		ExtractableResponse<Response> response = requestBoardDeleteApi(boardSeq);

		// 삭제 응답 검증
		AssertionsForClassTypes.assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
	}

	private static ExtractableResponse<Response> requestBoardCreateApi(Long memberSeq, BoardCreateRequest boardCreateRequest) {
		return RestAssured.given().log().all()
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.body(boardCreateRequest)
				.when()
				.post("/api/board/{memberSeq}/upload", memberSeq)
				.then()
				.log().all().extract();
	}

	private static ResponseBody requestSearchBoardApi(Long boardSeq) {
		return RestAssured.given().log().all()
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.when()
				.get("/api/board/{boardSeq}", boardSeq)
				.then()
				.log().all().extract().response()
				.getBody();
	}

	private static ResponseBody requestSearchBoardsByMemberApi(Long memberSeq) {
		return RestAssured.given().log().all()
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.when()
				.get("/api/board/member/{memberSeq}", memberSeq)
				.then()
				.log().all().extract().response()
				.getBody();
	}

	private static ExtractableResponse<Response> requestUpdateBoard(Long boardSeq, BoardDto boardDto) {
		return RestAssured.given().log().all()
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.body(boardDto)
				.when()
				.put("/api/board/{boardSeq}", boardSeq)
				.then()
				.log().all().extract();
	}

	private static ExtractableResponse<Response> requestBoardDeleteApi(Long boardSeq) {
		return RestAssured.given().log().all()
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.when()
				.delete("/api/board/{boardSeq}", boardSeq)
				.then()
				.log().all().extract();
	}

	private static BoardCreateRequest createBoardCreateRequest(Long memberSeq) {
		String content = "새로운 게시물입니다.";
		String boardImage = "images/img01.jpg";
		return BoardCreateRequest.of(memberSeq, content, boardImage);
	}

	private static BoardDto createBoardDto(Long boardSeq, Long memberSeq, LocalDateTime createdDate) {
		String content = "업데이트된 게시물입니다.";
		String boardImage = "imags/update01.jpg";
		return BoardDto.builder()
				.boardSeq(boardSeq)
				.memberSeq(memberSeq)
				.content(content)
				.boardImage(boardImage)
				.build();
	}

	private static MemberCreateRequest createMemberCreateRequest(String memberId, String password, String memberName, String email) {
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
