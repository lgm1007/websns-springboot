package com.lgmpjt.websnsspringboot.adapter.in.rest;

import com.lgmpjt.websnsspringboot.application.port.in.dto.FollowDto;
import com.lgmpjt.websnsspringboot.application.port.service.FollowService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/follow")
@RequiredArgsConstructor
public class FollowController {
	private final FollowService followService;

	@PostMapping("/{fromFollow}/to/{toFollow}")
	@Operation(summary = "팔로우 수행", description = "{fromFollow} 유저가 {toFollow} 유저를 팔로우합니다.")
	public ResponseEntity<Void> doFollow(@PathVariable final Long fromFollow, @PathVariable final Long toFollow) {
		followService.saveFollow(fromFollow, toFollow);

		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@GetMapping("/{userSeq}/following")
	@Operation(summary = "특정 유저의 팔로잉 조회", description = "특정 유저가 팔로잉하고 있는 대상의 목록을 조회합니다.")
	public List<FollowDto> searchFollowing(@PathVariable final Long userSeq) {
		return followService.findAllFollowingByUser(userSeq);
	}

	@GetMapping("/{userSeq}/follower")
	@Operation(summary = "특정 유저의 팔로워 조회", description = "특정 유저를 팔로우하는 대상의 목록을 조회합니다.")
	public List<FollowDto> searchFollower(@PathVariable final Long userSeq) {
		return followService.findAllFollowerByUser(userSeq);
	}

	@DeleteMapping("/{fromFollow}/to/{toFollow}")
	@Operation(summary = "언팔로우", description = "{fromFollow} 유저가 {toFollow} 유저를 언팔로우합니다.")
	public ResponseEntity<Pair<Long, Long>> doUnfollow(@PathVariable final Long fromFollow, @PathVariable final Long toFollow) {
		followService.deleteFollow(fromFollow, toFollow);

		return ResponseEntity.ok(Pair.of(fromFollow, toFollow));
	}
}
