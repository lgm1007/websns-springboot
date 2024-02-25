package com.lgmpjt.websnsspringboot.adapter.in.rest;

import com.lgmpjt.websnsspringboot.application.port.in.dto.UserCreateDto;
import com.lgmpjt.websnsspringboot.application.port.in.dto.UserDto;
import com.lgmpjt.websnsspringboot.domain.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;

	@PostMapping
	@Operation(summary = "신규 유저 생성", description = "회원가입한 신규 유저를 생성해줍니다.")
	public ResponseEntity<Void> createNewUser(@RequestBody final UserCreateDto userCreateDto) {
		userService.createUser(userCreateDto);

		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@GetMapping("/{userSeq}")
	@Operation(summary = "단일 유저 조회", description = "단일 유저 정보를 조회합니다.")
	public UserDto searchOneUser(@PathVariable final Long userSeq) {
		return userService.getUserByUserSeq(userSeq);
	}

	@PutMapping("/{userSeq}")
	@Operation(summary = "단일 유저 업데이트", description = "단일 유저 정보를 업데이트합니다.")
	public ResponseEntity<Long> updateOneUser(@PathVariable final Long userSeq,
											  @RequestBody final UserDto userDto) {
		userService.updateUser(userDto);

		return ResponseEntity.ok(userSeq);
	}

	@DeleteMapping("/{userSeq}")
	@Operation(summary = "단일 유저 삭제", description = "단일 유저를 삭제합니다.")
	public ResponseEntity<Long> deleteOneUser(@PathVariable final Long userSeq) {
		userService.deleteUser(userSeq);

		return ResponseEntity.ok(userSeq);
	}
}
