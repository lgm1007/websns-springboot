package com.lgmpjt.websnsspringboot.user.data;

import lombok.Builder;
import org.springframework.util.Assert;

import java.time.LocalDateTime;

@Builder
public class UserCreateDto {
	private String userId;
	private String password;
	private String userName;
	private String userEmail;
	LocalDateTime createdDate;
	LocalDateTime lastModifiedDate;
	boolean admin;

	public UserCreateDto(String userId, String password, String userName, String userEmail, boolean admin) {
		Assert.hasText(userId, "유저 아이디 값은 필수입니다.");
		Assert.notNull(password, "패스워드 값은 필수입니다.");
		Assert.hasText(userName, "유저 이름 값은 필수입니다.");
		this.userId = userId;
		this.password = password;
		this.userName = userName;
		this.userEmail = userEmail;
		this.admin = admin;
		this.createdDate = LocalDateTime.now();
		this.lastModifiedDate = null;
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

	public boolean isAdmin() {
		return admin;
	}
}
