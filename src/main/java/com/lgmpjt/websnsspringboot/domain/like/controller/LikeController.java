package com.lgmpjt.websnsspringboot.domain.like.controller;

import com.lgmpjt.websnsspringboot.domain.like.data.LikeDto;
import com.lgmpjt.websnsspringboot.domain.like.service.LikeService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
