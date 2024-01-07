package com.lgmpjt.websnsspringboot.user.data;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.util.Assert;

@ToString
@Getter
@Setter
@Builder
public class UserSearchUpdateDto {
	Long userSeq;
	String userId;
	String password;
	String userName;
	String userEmail;

	public UserSearchUpdateDto(final Long userSeq, final String userId, final String password, final String userName, final String userEmail) {
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
}
