package com.lgmpjt.websnsspringboot.domain.board.controller;

import com.lgmpjt.websnsspringboot.domain.board.data.BoardCreateDto;
import com.lgmpjt.websnsspringboot.domain.board.data.BoardDto;
import com.lgmpjt.websnsspringboot.domain.board.service.BoardService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardController {
	private final BoardService boardService;

	@PostMapping("/{userSeq}/upload")
	@Operation(summary = "게시물 업로드", description = "유저가 게시물을 업로드합니다.")
	public ResponseEntity<Void> createNewBoard(@PathVariable final Long userSeq,
											   @RequestBody final BoardCreateDto boardCreateDto) {
		boardService.createBoard(boardCreateDto);

		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@GetMapping("/{boardSeq}")
	@Operation(summary = "단일 게시물 조회", description = "단일 게시물 정보를 조회합니다.")
	public BoardDto searchBoard(@PathVariable final Long boardSeq) {
		return boardService.getBoardByBoardSeq(boardSeq);
	}

	@GetMapping("user/{userSeq}")
	@Operation(summary = "단일 유저의 게시물 조회", description = "단일 유저가 업로드한 게시물을 조회합니다.")
	public List<BoardDto> searchBoardsByUserSeq(@PathVariable final Long userSeq) {
		return boardService.searchBoardsByUserSeq(userSeq);
	}

	@PutMapping("/{boardSeq}")
	@Operation(summary = "단일 게시물 업데이트", description = "단일 게시물 정보를 업데이트합니다.")
	public ResponseEntity<Long> updateBoard(@PathVariable final Long boardSeq,
											@RequestBody final BoardDto boardDto) {
		boardService.updateBoard(boardDto);

		return ResponseEntity.ok(boardDto.getBoardSeq());
	}

	@DeleteMapping("/{boardSeq}")
	@Operation(summary = "단일 게시물 삭제", description = "단일 게시물을 삭제합니다.")
	public ResponseEntity<Long> deleteBoard(@PathVariable final Long boardSeq) {
		boardService.deleteBoard(boardSeq);

		return ResponseEntity.ok(boardSeq);
	}
}
