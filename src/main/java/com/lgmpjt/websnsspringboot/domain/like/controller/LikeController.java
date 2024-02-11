package com.lgmpjt.websnsspringboot.domain.like.controller;

import com.lgmpjt.websnsspringboot.domain.like.data.LikeDto;
import com.lgmpjt.websnsspringboot.domain.like.service.LikeService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/likes")
@RequiredArgsConstructor
public class LikeController {

	private final LikeService likeService;

	@PostMapping("/do/like")
	@Operation(summary = "해당 게시물 좋아요", description = "유저가 해당 게시물에 좋아요합니다.")
	public ResponseEntity<Void> doLikeBoard(@RequestBody final LikeDto likeDto) {
		likeService.createLike(likeDto);

		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@DeleteMapping("/undo/{userSeq}/to/{boardSeq}")
	@Operation(summary = "게시물 좋아요 취소", description = "유저가 이전에 한 좋아요를 취소합니다.")
	public ResponseEntity<Void> undoLikeBoard(@PathVariable final Long userSeq, @PathVariable final Long boardSeq) {
		likeService.deleteLike(userSeq, boardSeq);

		return ResponseEntity.status(HttpStatus.OK).build();
	}

}
