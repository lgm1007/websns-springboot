package com.lgmpjt.websnsspringboot.adapter.in.rest;

import com.lgmpjt.websnsspringboot.application.port.in.dto.BoardDto;
import com.lgmpjt.websnsspringboot.domain.like.service.LikeService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/like")
@RequiredArgsConstructor
public class LikeController {

	private final LikeService likeService;

	@PostMapping("/{userSeq}/to/{boardSeq}")
	@Operation(summary = "해당 게시물 좋아요", description = "유저가 해당 게시물에 좋아요합니다.")
	public ResponseEntity<Void> doLikeBoard(@PathVariable final Long userSeq, @PathVariable final Long boardSeq) {
		likeService.createLike(userSeq, boardSeq);

		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@DeleteMapping("/undo/{userSeq}/to/{boardSeq}")
	@Operation(summary = "게시물 좋아요 취소", description = "유저가 이전에 한 좋아요를 취소합니다.")
	public ResponseEntity<Void> undoLikeBoard(@PathVariable final Long userSeq, @PathVariable final Long boardSeq) {
		likeService.deleteLike(userSeq, boardSeq);

		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@GetMapping("/list/{userSeq}")
	@Operation(summary = "특정 유저가 좋아요 한 게시글 목록", description = "특정 유저가 좋아요 한 게시글 목록을 조회합니다.")
	public List<BoardDto> getBoardsByUserLike(@PathVariable final Long userSeq) {
		return likeService.getLikeBoardByUser(userSeq);
	}

}
