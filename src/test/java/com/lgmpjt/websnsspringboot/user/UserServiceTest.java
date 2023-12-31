package com.lgmpjt.websnsspringboot.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserServiceTest {

	private UserService userService;
	private UserPort userPort;
	private UserRepository userRepository;

	@BeforeEach
	void setUp() {
		userRepository = new UserRepository();
		userPort = new UserAdapter(userRepository);
		userService = new UserService(userPort);
	}

	@Test
	void createUser() {
		final UserCreateDto userCreateDto = requestUserCreateDto();
		userService.createUser(userCreateDto);
	}

	private static UserCreateDto requestUserCreateDto() {
		String userId = "userId";
		String password = "password";
		String userName = "홍길동";
		String userEmail = "mysns@example.com";
		return new UserCreateDto(userId, password, userName, userEmail);
	}

}
