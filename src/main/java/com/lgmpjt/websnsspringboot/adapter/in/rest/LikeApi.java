package com.lgmpjt.websnsspringboot.adapter.in.rest;

import com.lgmpjt.websnsspringboot.application.port.in.LikeCommandUseCase;
import com.lgmpjt.websnsspringboot.application.port.in.LikeSearchUseCase;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/like")
@RequiredArgsConstructor
public class LikeApi {

	private final LikeSearchUseCase searchUseCase;
	private final LikeCommandUseCase commandUseCase;

	@PostMapping("/{memberSeq}/to/{boardSeq}")
	@Operation(summary = "해당 게시물 좋아요", description = "유저가 해당 게시물에 좋아요합니다.")
	public ResponseEntity<Void> doLikeBoard(@PathVariable final Long memberSeq, @PathVariable final Long boardSeq) {
		commandUseCase.createLike(memberSeq, boardSeq);

		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@DeleteMapping("/undo/{memberSeq}/to/{boardSeq}")
	@Operation(summary = "게시물 좋아요 취소", description = "유저가 이전에 한 좋아요를 취소합니다.")
	public ResponseEntity<Void> undoLikeBoard(@PathVariable final Long memberSeq, @PathVariable final Long boardSeq) {
		commandUseCase.deleteLike(memberSeq, boardSeq);

		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@GetMapping("/list/{memberSeq}")
	@Operation(summary = "특정 유저가 좋아요 한 게시글 목록", description = "특정 유저가 좋아요 한 게시글 목록을 조회합니다.")
	public List<Long> getBoardsByMemberLike(@PathVariable final Long memberSeq) {
		return searchUseCase.findAllLikeBoardSeqByMemberSeq(memberSeq);
	}

}
