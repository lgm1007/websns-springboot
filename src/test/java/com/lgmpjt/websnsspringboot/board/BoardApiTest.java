package com.lgmpjt.websnsspringboot.board;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.time.LocalDateTime;

public class BoardApiTest {
	@Autowired
	private BoardService boardService;

	@Test
	void createBoard() {
		final Long userSeq = 1L;

		// 게시물 생성
		BoardCreateDto boardDto = requestBoardCreateDto(userSeq);

		// 게시물 업로드 API 요청
		ExtractableResponse<Response> response = requestBoardCreateApi(userSeq, boardDto);

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

	private static BoardCreateDto requestBoardCreateDto(Long userSeq) {
		String content = "새로운 게시물입니다.";
		String boardImage = "images/img01.jpg";
		LocalDateTime createdDate = LocalDateTime.now();
		return new BoardCreateDto(userSeq, content, boardImage, createdDate);
	}
}
