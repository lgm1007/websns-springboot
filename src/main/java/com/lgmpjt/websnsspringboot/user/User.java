package com.lgmpjt.websnsspringboot.user;

import org.springframework.util.Assert;

public class User {

	private Long userSeq;
	private final String userId;
	private final String password;
	private final String userName;
	private final String userEmail;

	public User(String userId, String password, String userName, String userEmail) {
		Assert.hasText(userId, "유저 아이디 값은 필수입니다.");
		Assert.notNull(password, "패스워드 값은 필수입니다.");
		Assert.hasText(userName, "유저 이름 값은 필수입니다.");
		this.userId = userId;
		this.password = password;
		this.userName = userName;
		this.userEmail = userEmail;
	}

	public void assignId(Long seq) {
		this.userSeq = seq;
	}

	public Long getUserSeq() {
		return userSeq;
	}
}
