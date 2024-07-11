package com.lgmpjt.websnsspringboot.adapter.in.rest;

import com.lgmpjt.websnsspringboot.adapter.in.rest.request.BoardCreateRequest;
import com.lgmpjt.websnsspringboot.application.port.in.BoardCommandUseCase;
import com.lgmpjt.websnsspringboot.application.port.in.BoardSearchUseCase;
import com.lgmpjt.websnsspringboot.application.port.in.dto.BoardDto;
import com.lgmpjt.websnsspringboot.application.port.service.dto.BoardServiceDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardApi {
	private final BoardSearchUseCase searchUseCase;
	private final BoardCommandUseCase commandUseCase;

	@PostMapping("/{memberSeq}/upload")
	@Operation(summary = "게시물 업로드", description = "유저가 게시물을 업로드합니다.")
	public ResponseEntity<Void> createNewBoard(@PathVariable final Long memberSeq,
											   @RequestBody final BoardCreateRequest boardCreateRequest) {
		commandUseCase.createBoard(BoardServiceDto.from(boardCreateRequest));

		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@GetMapping("/{boardSeq}")
	@Operation(summary = "단일 게시물 조회", description = "단일 게시물 정보를 조회합니다.")
	public BoardDto searchBoard(@PathVariable final Long boardSeq) {
		return searchUseCase.getBoardByBoardSeq(boardSeq);
	}

	@GetMapping("member/{memberSeq}")
	@Operation(summary = "단일 유저의 게시물 조회", description = "단일 유저가 업로드한 게시물을 조회합니다.")
	public List<BoardDto> searchBoardsByMemberSeq(@PathVariable final Long memberSeq) {
		return searchUseCase.findAllBoardsByMemberSeq(memberSeq);
	}

	@PutMapping("/{boardSeq}")
	@Operation(summary = "단일 게시물 업데이트", description = "단일 게시물 정보를 업데이트합니다.")
	public ResponseEntity<Long> updateBoard(@PathVariable final Long boardSeq,
											@RequestBody final BoardDto boardDto) {
		commandUseCase.updateBoard(boardDto);

		return ResponseEntity.ok(boardDto.getBoardSeq());
	}

	@DeleteMapping("/{boardSeq}")
	@Operation(summary = "단일 게시물 삭제", description = "단일 게시물을 삭제합니다.")
	public ResponseEntity<Long> deleteBoard(@PathVariable final Long boardSeq) {
		commandUseCase.deleteBoard(boardSeq);

		return ResponseEntity.ok(boardSeq);
	}
}
