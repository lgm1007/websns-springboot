package com.lgmpjt.websnsspringboot.user.data;

import lombok.Builder;
import org.springframework.util.Assert;

@Builder
public class UserCreateDto {
	private String userId;
	private String password;
	private String userName;
	private String userEmail;

	public UserCreateDto(String userId, String password, String userName, String userEmail) {
		Assert.hasText(userId, "유저 아이디 값은 필수입니다.");
		Assert.notNull(password, "패스워드 값은 필수입니다.");
		Assert.hasText(userName, "유저 이름 값은 필수입니다.");
		this.userId = userId;
		this.password = password;
		this.userName = userName;
		this.userEmail = userEmail;
	}

	public String getUserId() {
		return userId;
	}

	public String getPassword() {
		return password;
	}

	public String getUserName() {
		return userName;
	}

	public String getUserEmail() {
		return userEmail;
	}
}
