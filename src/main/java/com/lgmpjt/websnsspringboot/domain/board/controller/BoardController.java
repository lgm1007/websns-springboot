package com.lgmpjt.websnsspringboot.domain.board.controller;

import com.lgmpjt.websnsspringboot.domain.board.data.BoardCreateDto;
import com.lgmpjt.websnsspringboot.domain.board.data.BoardDto;
import com.lgmpjt.websnsspringboot.domain.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
