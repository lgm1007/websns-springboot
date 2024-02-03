package com.lgmpjt.websnsspringboot.domain.board.controller;

import com.lgmpjt.websnsspringboot.domain.board.data.BoardCreateDto;
import com.lgmpjt.websnsspringboot.domain.board.data.BoardDto;
import com.lgmpjt.websnsspringboot.domain.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {
	private final BoardService boardService;

	@PostMapping("/{userSeq}/upload")
	public ResponseEntity<Void> createNewBoard(@PathVariable final Long userSeq,
											   @RequestBody final BoardCreateDto boardCreateDto) {
		boardService.createBoard(boardCreateDto);

		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@GetMapping("/{boardSeq}")
	public BoardDto searchBoard(@PathVariable final Long boardSeq) {
		return boardService.searchBoard(boardSeq);
	}

	@GetMapping("user/{userSeq}")
	public List<BoardDto> searchBoardsByUserSeq(@PathVariable final Long userSeq) {
		return boardService.searchBoardsByUserSeq(userSeq);
	}

	@PutMapping("/{boardSeq}")
	public ResponseEntity<Long> updateBoard(@PathVariable final Long boardSeq,
											@RequestBody final BoardDto boardDto) {
		boardService.updateBoard(boardDto);

		return ResponseEntity.ok(boardDto.getBoardSeq());
	}

	@DeleteMapping("/{boardSeq}")
	public ResponseEntity<Long> deleteBoard(@PathVariable final Long boardSeq) {
		boardService.deleteBoard(boardSeq);

		return ResponseEntity.ok(boardSeq);
	}
}
