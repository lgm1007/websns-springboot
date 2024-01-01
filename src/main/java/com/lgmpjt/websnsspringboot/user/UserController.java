package com.lgmpjt.websnsspringboot.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
	private final UserPort userPort;

	@PostMapping
	public ResponseEntity<Void> createNewUser(@RequestBody final UserCreateDto userCreateDto) {
		final User user = new User(userCreateDto.getUserId(), userCreateDto.getPassword(), userCreateDto.getUserName(), userCreateDto.getUserEmail());

		userPort.save(user);

		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
}
