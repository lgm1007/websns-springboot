package com.lgmpjt.websnsspringboot.application.port.in.dto;

import lombok.*;
import org.springframework.util.Assert;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class MemberCreateDto {
	private String memberId;
	private String password;
	private String memberName;
	private String email;
	private boolean isAdmin;
	private boolean isPrivate;

	@Builder
	public MemberCreateDto(String memberId, String password, String memberName, String email, boolean isAdmin, boolean isPrivate) {
		Assert.hasText(memberId, "유저 아이디 값은 필수입니다.");
		Assert.notNull(password, "패스워드 값은 필수입니다.");
		Assert.hasText(memberName, "유저 이름 값은 필수입니다.");
		this.memberId = memberId;
		this.password = password;
		this.memberName = memberName;
		this.email = email;
		this.isAdmin = isAdmin;
		this.isPrivate = isPrivate;
	}
}
