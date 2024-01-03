package com.lgmpjt.websnsspringboot.user.data;

import org.springframework.util.Assert;

public class UserSearchDto {
	Long userSeq;
	String userId;
	String userName;
	String userEmail;

	public UserSearchDto(final Long userSeq, final String userId, final String userName, final String userEmail) {
		Assert.notNull(userSeq, "유저 SEQ 값은 필수입니다.");
		Assert.hasText(userId, "유저 ID 값은 필수입니다.");
		Assert.hasText(userName, "유저 이름 값은 필수입니다.");
		this.userSeq = userSeq;
		this.userId = userId;
		this.userName = userName;
		this.userEmail = userEmail;
	}
}
