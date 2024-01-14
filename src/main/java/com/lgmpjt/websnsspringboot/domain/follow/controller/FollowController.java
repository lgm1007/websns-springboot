package com.lgmpjt.websnsspringboot.domain.follow.controller;

import com.lgmpjt.websnsspringboot.domain.follow.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/follows")
@RequiredArgsConstructor
public class FollowController {
	private final FollowService followService;

	@PostMapping("/{fromFollow}/to/{toFollow}")
	public ResponseEntity<Void> doFollow(@PathVariable final Long fromFollow, @PathVariable final Long toFollow) {
		followService.saveFollow(fromFollow, toFollow);

		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
}
