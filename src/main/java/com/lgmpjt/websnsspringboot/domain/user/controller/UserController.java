package com.lgmpjt.websnsspringboot.domain.user.controller;

import com.lgmpjt.websnsspringboot.domain.user.data.UserCreateDto;
import com.lgmpjt.websnsspringboot.domain.user.data.UserDto;
import com.lgmpjt.websnsspringboot.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;

	@PostMapping
	public ResponseEntity<Void> createNewUser(@RequestBody final UserCreateDto userCreateDto) {
		userService.createUser(userCreateDto);

		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@GetMapping("/{userSeq}")
	public UserDto searchOneUser(@PathVariable final Long userSeq) {
		return userService.searchUser(userSeq);
	}

	@PutMapping("/{userSeq}")
	public ResponseEntity<Long> updateOneUser(@PathVariable final Long userSeq,
											  @RequestBody final UserDto userDto) {
		userService.updateUser(userDto);

		return ResponseEntity.ok(userSeq);
	}

	@DeleteMapping("/{userSeq}")
	public ResponseEntity<Long> deleteOneUser(@PathVariable final Long userSeq) {
		userService.withdrawUser(userSeq);

		return ResponseEntity.ok(userSeq);
	}
}
