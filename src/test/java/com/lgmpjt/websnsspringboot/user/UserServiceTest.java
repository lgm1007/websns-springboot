package com.lgmpjt.websnsspringboot.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles(value = "test")
class UserServiceTest {

	@Autowired
	private UserService userService;

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
