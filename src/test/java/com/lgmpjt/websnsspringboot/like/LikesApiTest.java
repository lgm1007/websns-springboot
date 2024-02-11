package com.lgmpjt.websnsspringboot.like;

import com.lgmpjt.websnsspringboot.ApiTest;
import com.lgmpjt.websnsspringboot.domain.board.data.BoardCreateDto;
import com.lgmpjt.websnsspringboot.domain.board.data.BoardDto;
import com.lgmpjt.websnsspringboot.domain.board.service.BoardService;
import com.lgmpjt.websnsspringboot.domain.like.data.LikeDto;
import com.lgmpjt.websnsspringboot.domain.like.service.LikeService;
import com.lgmpjt.websnsspringboot.domain.user.data.UserCreateDto;
import com.lgmpjt.websnsspringboot.domain.user.data.UserDto;
import com.lgmpjt.websnsspringboot.domain.user.service.UserService;
import com.lgmpjt.websnsspringboot.mapper.board.BoardMapper;
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

public class LikesApiTest extends ApiTest {

	@Autowired
	private UserService userService;

	@Autowired
	private BoardService boardService;

	@Autowired
	private LikeService likeService;

	@Test
	void doLike() {
		// 유저 생성
		UserDto userDto = UserMapper.INSTANCE.toUserSearchDto(
				userService.createUser(requestUserCreateDto("userId1", "1234", "David", "david@example.com"))
		);

		// 게시물 생성
		BoardDto boardDto = BoardMapper.INSTANCE.boardToDto(
				boardService.createBoard(requestBoardCreateDto(userDto))
		);

		// 좋아요 DTO 생성
		final LikeDto likeDto = new LikeDto(userDto, boardDto, LocalDateTime.now());
		
		// 게시물 좋아요하기
		ExtractableResponse<Response> response = requestDoLike(likeDto);

		// 응답값 검증
		AssertionsForClassTypes.assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
	}

	private static ExtractableResponse<Response> requestDoLike(final LikeDto likeDto) {
		return RestAssured.given().log().all()
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.body(likeDto)
				.when()
				.post("/likes/do/like")
				.then()
				.log().all().extract();
	}

	private static UserCreateDto requestUserCreateDto(String userId, String password, String userName, String userEmail) {
		boolean isAdmin = false;
		boolean isPrivate = false;
		return new UserCreateDto(userId, password, userName, userEmail, isAdmin, isPrivate);
	}

	private static BoardCreateDto requestBoardCreateDto(UserDto userDto) {
		String content = "새로운 게시물입니다.";
		String boardImage = "images/img01.jpg";
		return new BoardCreateDto(userDto, content, boardImage, LocalDateTime.now());
	}
}
