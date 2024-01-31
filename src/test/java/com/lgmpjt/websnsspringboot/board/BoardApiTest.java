package com.lgmpjt.websnsspringboot.board;

import com.lgmpjt.websnsspringboot.ApiTest;
import com.lgmpjt.websnsspringboot.domain.board.data.BoardCreateDto;
import com.lgmpjt.websnsspringboot.domain.board.data.BoardDto;
import com.lgmpjt.websnsspringboot.domain.board.service.BoardService;
import com.lgmpjt.websnsspringboot.domain.user.data.UserCreateDto;
import com.lgmpjt.websnsspringboot.domain.user.data.UserDto;
import com.lgmpjt.websnsspringboot.domain.user.service.UserService;
import com.lgmpjt.websnsspringboot.mapper.user.UserMapper;
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
	private UserService userService;

	@Autowired
	private BoardService boardService;

	@Test
	void createBoard() {
		// 유저 생성
		UserDto userDto = UserMapper.INSTANCE.toUserSearchDto(
			userService.createUser(requestUserCreateDto("userId1", "1234", "David", "david@example.com", false))
		);

		// 게시물 생성
		BoardCreateDto boardDto = requestBoardCreateDto(userDto);

		// 게시물 업로드 API 요청
		ExtractableResponse<Response> response = requestBoardCreateApi(userDto.getUserSeq(), boardDto);

		// 업로드 응답 검증
		AssertionsForClassTypes.assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
	}

	@Test
	void searchBoard() {
		// 유저 생성
		UserDto userDto = UserMapper.INSTANCE.toUserSearchDto(
				userService.createUser(requestUserCreateDto("userId1", "1234", "David", "david@example.com", false))
		);

		// 게시물 생성
		Long boardSeq = boardService.createBoard(requestBoardCreateDto(userDto)).getBoardSeq();

		// 게시물 조회 API 요청
		ResponseBody body = requestSearchBoardApi(boardSeq);

		// 조회 응답 검증
		AssertionsForClassTypes.assertThat(body.as(BoardDto.class).getBoardSeq()).isEqualTo(boardSeq);
	}
	
	@Test
	void searchBoardsByUserSeq() {
		// 유저 생성
		UserDto userDto = UserMapper.INSTANCE.toUserSearchDto(
				userService.createUser(requestUserCreateDto("userId1", "1234", "David", "david@example.com", false))
		);

		// 게시물 생성
		boardService.createBoard(requestBoardCreateDto(userDto));

		ResponseBody body = requestSearchBoardsByUserApi(userDto.getUserSeq());

		// 조회 응답 검증
		AssertionsForClassTypes.assertThat(body.as(ArrayList.class).size()).isEqualTo(1);
	}

	@Test
	void updateBoard() {
		// 유저 생성
		UserDto userDto = UserMapper.INSTANCE.toUserSearchDto(
				userService.createUser(requestUserCreateDto("userId1", "1234", "David", "david@example.com", false))
		);

		// 게시물 생성
		Long boardSeq = boardService.createBoard(requestBoardCreateDto(userDto)).getBoardSeq();

		// 업데이트 내용 포함된 BoardDto 생성
		BoardDto boardDto = requestBoardDto(boardSeq, userDto, LocalDateTime.now());

		// 업데이트 API 요청
		ExtractableResponse<Response> response = requestUpdateBoard(boardSeq, boardDto);

		// 업데이트 응답 검증
		AssertionsForClassTypes.assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
	}

	private static ExtractableResponse<Response> requestBoardCreateApi(Long userSeq, BoardCreateDto boardCreateDto) {
		return RestAssured.given().log().all()
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.body(boardCreateDto)
				.when()
				.post("/boards/{userSeq}/upload", userSeq)
				.then()
				.log().all().extract();
	}

	private static ResponseBody requestSearchBoardApi(Long boardSeq) {
		return RestAssured.given().log().all()
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.when()
				.get("/boards/{boardSeq}", boardSeq)
				.then()
				.log().all().extract().response()
				.getBody();
	}

	private static ResponseBody requestSearchBoardsByUserApi(Long userSeq) {
		return RestAssured.given().log().all()
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.when()
				.get("/boards/user/{userSeq}", userSeq)
				.then()
				.log().all().extract().response()
				.getBody();
	}

	private static ExtractableResponse<Response> requestUpdateBoard(Long boardSeq, BoardDto boardDto) {
		return RestAssured.given().log().all()
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.body(boardDto)
				.when()
				.put("/boards/{boardSeq}", boardSeq)
				.then()
				.log().all().extract();
	}

	private static BoardCreateDto requestBoardCreateDto(UserDto userDto) {
		String content = "새로운 게시물입니다.";
		String boardImage = "images/img01.jpg";
		return new BoardCreateDto(userDto, content, boardImage);
	}

	private static BoardDto requestBoardDto(Long boardSeq, UserDto userDto, LocalDateTime createdDate) {
		String content = "업데이트된 게시물입니다.";
		String boardImage = "imags/update01.jpg";
		return new BoardDto(boardSeq, userDto, content, boardImage, createdDate);
	}

	private static UserCreateDto requestUserCreateDto(String userId, String password, String userName, String userEmail, boolean isAdmin) {
		return new UserCreateDto(userId, password, userName, userEmail, isAdmin);
	}
}
