package com.lgmpjt.websnsspringboot.application.port.in.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lgmpjt.websnsspringboot.adapter.out.persistence.entity.Member;
import com.lgmpjt.websnsspringboot.application.port.in.enumeration.MemberGrant;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.util.Assert;

@ToString
@Getter
public class MemberDto {
	private final Long memberSeq;
	private final String memberId;
	private final String password;
	private final String memberName;
	private final String email;
	private final boolean isAdmin;
	private final boolean isPrivate;
	private final boolean deleted;

	@Builder
	public MemberDto(final Long memberSeq, final String memberId, final String password, final String memberName, final String email, final boolean isAdmin, final boolean isPrivate, final boolean deleted) {
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
		this.deleted = deleted;
	}

	@JsonIgnore
	public MemberGrant getMemberGrant() {
		return (isAdmin) ? MemberGrant.ADMIN : MemberGrant.USER;
	}

	public static MemberDto from(final Member member) {
		return MemberDto.builder()
			.memberSeq(member.getMemberSeq())
			.password(member.getPassword())
			.memberId(member.getMemberId())
			.memberName(member.getMemberName())
			.email(member.getEmail())
			.isAdmin(member.isAdmin())
			.isPrivate(member.isPrivate())
			.build();
	}
}
