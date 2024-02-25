package com.lgmpjt.websnsspringboot.application.port.in.dto;

import lombok.*;
import org.springframework.util.Assert;

import java.time.LocalDateTime;

@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
	Long userSeq;
	String userId;
	String password;
	String userName;
	String userEmail;
	LocalDateTime createdDate;
	LocalDateTime lastModifiedDate;
	boolean isAdmin;
	boolean isPrivate;

	public UserDto(final Long userSeq, final String userId, final String password, final String userName, final String userEmail, final LocalDateTime createdDate, final boolean isAdmin, final boolean isPrivate) {
		Assert.notNull(userSeq, "유저 SEQ 값은 필수입니다.");
		Assert.notNull(password, "패스워드 값은 필수입니다.");
		Assert.hasText(userId, "유저 ID 값은 필수입니다.");
		Assert.hasText(userName, "유저 이름 값은 필수입니다.");
		this.userSeq = userSeq;
		this.password = password;
		this.userId = userId;
		this.userName = userName;
		this.userEmail = userEmail;
		this.isAdmin = isAdmin;
		this.isPrivate = isPrivate;
		this.createdDate = createdDate;
		this.lastModifiedDate = LocalDateTime.now();
	}
}