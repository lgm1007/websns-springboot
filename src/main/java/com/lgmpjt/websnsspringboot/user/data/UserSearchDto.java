package com.lgmpjt.websnsspringboot.user.data;

import lombok.Builder;
import org.springframework.util.Assert;

@Builder
public class UserSearchDto {
	Long userSeq;
	String userId;
	String password;
	String userName;
	String userEmail;

	public UserSearchDto(final Long userSeq, final String userId, final String password, final String userName, final String userEmail) {
		Assert.notNull(userSeq, "유저 SEQ 값은 필수입니다.");
		Assert.notNull(password, "패스워드 값은 필수입니다.");
		Assert.hasText(userId, "유저 ID 값은 필수입니다.");
		Assert.hasText(userName, "유저 이름 값은 필수입니다.");
		this.userSeq = userSeq;
		this.password = password;
		this.userId = userId;
		this.userName = userName;
		this.userEmail = userEmail;
	}

	public Long getUserSeq() {
		return userSeq;
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
