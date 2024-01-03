package com.lgmpjt.websnsspringboot.user.controller;

import com.lgmpjt.websnsspringboot.user.data.UserCreateDto;
import com.lgmpjt.websnsspringboot.user.data.UserSearchDto;
import com.lgmpjt.websnsspringboot.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;

	@GetMapping("/{userSeq}")
	public ResponseEntity<UserSearchDto> findOneUser(@PathVariable final Long userSeq) {
		return ResponseEntity.ok(userService.findUser(userSeq));
	}

	@PostMapping
	public ResponseEntity<Void> createNewUser(@RequestBody final UserCreateDto userCreateDto) {
		userService.createUser(userCreateDto);

		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
}
