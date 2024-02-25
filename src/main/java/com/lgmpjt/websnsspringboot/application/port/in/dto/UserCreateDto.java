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
public class UserCreateDto {
	private String userId;
	private String password;
	private String userName;
	private String userEmail;
	private LocalDateTime createdDate;
	private LocalDateTime lastModifiedDate;
	private boolean isAdmin;
	private boolean isPrivate;

	public UserCreateDto(String userId, String password, String userName, String userEmail, boolean isAdmin, boolean isPrivate) {
		Assert.hasText(userId, "유저 아이디 값은 필수입니다.");
		Assert.notNull(password, "패스워드 값은 필수입니다.");
		Assert.hasText(userName, "유저 이름 값은 필수입니다.");
		this.userId = userId;
		this.password = password;
		this.userName = userName;
		this.userEmail = userEmail;
		this.isAdmin = isAdmin;
		this.isPrivate = isPrivate;
		this.createdDate = LocalDateTime.now();
		this.lastModifiedDate = null;
	}
}
