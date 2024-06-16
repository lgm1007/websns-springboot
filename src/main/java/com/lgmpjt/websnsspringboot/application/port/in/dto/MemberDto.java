package com.lgmpjt.websnsspringboot.application.port.in.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lgmpjt.websnsspringboot.application.port.in.enumeration.MemberGrant;
import lombok.*;
import org.springframework.util.Assert;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class MemberDto {
	Long memberSeq;
	String memberId;
	String password;
	String memberName;
	String email;
	boolean isAdmin;
	boolean isPrivate;
	boolean deleted;

	@Builder
	public MemberDto(final Long memberSeq, final String memberId, final String password, final String memberName, final String email, final boolean isAdmin, final boolean isPrivate) {
		Assert.notNull(memberSeq, "유저 SEQ 값은 필수입니다.");
		Assert.notNull(password, "패스워드 값은 필수입니다.");
		Assert.hasText(memberId, "유저 ID 값은 필수입니다.");
		Assert.hasText(memberName, "유저 이름 값은 필수입니다.");
		this.memberSeq = memberSeq;
		this.password = password;
		this.memberId = memberId;
		this.memberName = memberName;
		this.email = email;
		this.isAdmin = isAdmin;
		this.isPrivate = isPrivate;
	}

	@JsonIgnore
	public MemberGrant getMemberGrant() {
		return (isAdmin) ? MemberGrant.ADMIN : MemberGrant.USER;
	}
}
