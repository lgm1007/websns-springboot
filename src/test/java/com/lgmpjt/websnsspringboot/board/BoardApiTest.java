package com.lgmpjt.websnsspringboot.board;

import com.lgmpjt.websnsspringboot.ApiTest;
import com.lgmpjt.websnsspringboot.domain.board.data.BoardCreateDto;
import com.lgmpjt.websnsspringboot.domain.board.service.BoardService;
import com.lgmpjt.websnsspringboot.domain.user.data.UserCreateDto;
import com.lgmpjt.websnsspringboot.domain.user.data.UserSearchUpdateDto;
import com.lgmpjt.websnsspringboot.domain.user.service.UserService;
import com.lgmpjt.websnsspringboot.mapper.user.UserMapper;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.time.LocalDateTime;

public class BoardApiTest extends ApiTest {

	@Autowired
	private UserService userService;

	@Autowired
	private BoardService boardService;

	@Test
	void createBoard() {
		// 유저 생성
		UserSearchUpdateDto userDto = UserMapper.INSTANCE.toUserSearchDto(
			userService.createUser(requestUserCreateDto("userId1", "1234", "David", "david@example.com", false))
		);

		// 게시물 생성
		BoardCreateDto boardDto = requestBoardCreateDto(userDto);

		// 게시물 업로드 API 요청
		ExtractableResponse<Response> response = requestBoardCreateApi(userDto.getUserSeq(), boardDto);

		// 업로드 응답 검증
		AssertionsForClassTypes.assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
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

	private static BoardCreateDto requestBoardCreateDto(UserSearchUpdateDto userDto) {
		String content = "새로운 게시물입니다.";
		String boardImage = "images/img01.jpg";
		return new BoardCreateDto(userDto, content, boardImage);
	}

	private static UserCreateDto requestUserCreateDto(String userId, String password, String userName, String userEmail, boolean isAdmin) {
		return new UserCreateDto(userId, password, userName, userEmail, isAdmin);
	}
}
