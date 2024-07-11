package com.lgmpjt.websnsspringboot.application.port.service.dto;

import com.lgmpjt.websnsspringboot.adapter.in.rest.request.MemberCreateRequest;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberServiceDto {
	@NotNull
	private final String memberId;
	@NotNull
	private final String password;
	@NotNull
	private final String memberName;
	private final String email;
	private final boolean isAdmin;
	private final boolean isPrivate;

	public MemberServiceDto fetchPassword(final String password) {
		return MemberServiceDto.builder()
			.memberId(this.memberId)
			.password(password)
			.memberName(this.memberName)
			.email(this.email)
			.isAdmin(this.isAdmin)
			.isPrivate(this.isPrivate)
			.build();
	}

	public static MemberServiceDto from(final MemberCreateRequest memberCreateRequest) {
		return MemberServiceDto.builder()
			.memberId(memberCreateRequest.getMemberId())
			.password(memberCreateRequest.getPassword())
			.memberName(memberCreateRequest.getMemberName())
			.email(memberCreateRequest.getEmail())
			.isAdmin(memberCreateRequest.isAdmin())
			.isPrivate(memberCreateRequest.isPrivate())
			.build();
	}
}
