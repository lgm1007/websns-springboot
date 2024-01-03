package com.lgmpjt.websnsspringboot.user;

import com.lgmpjt.websnsspringboot.ApiTest;
import com.lgmpjt.websnsspringboot.user.data.UserCreateDto;
import com.lgmpjt.websnsspringboot.user.data.UserSerchDto;
import com.lgmpjt.websnsspringboot.user.service.UserService;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class UserServiceTest extends ApiTest {

	@Autowired
	private UserService userService;

	@Test
	void searchUser() {
		// 유저 생성
		userService.createUser(requestUserCreateDto());
		final Long userSeq = 1L;

		// 유저 조회
		final UserSerchDto response = userService.findUser(userSeq);

		// 조회 응답 검증
		AssertionsForClassTypes.assertThat(response).isNotNull();
	}

	public static UserCreateDto requestUserCreateDto() {
		String userId = "userId";
		String password = "password";
		String userName = "홍길동";
		String userEmail = "mysns@example.com";
		return new UserCreateDto(userId, password, userName, userEmail);
	}
}
