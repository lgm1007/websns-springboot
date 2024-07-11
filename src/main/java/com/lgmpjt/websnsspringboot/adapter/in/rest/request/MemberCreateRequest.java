package com.lgmpjt.websnsspringboot.adapter.in.rest.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberCreateRequest {
	@NotNull
	private final String memberId;
	@NotNull
	private final String password;
	@NotNull
	private final String memberName;
	private final String email;
	private final boolean isAdmin;
	private final boolean isPrivate;

	public static MemberCreateRequest of(final String memberId,
										 final String password,
										 final String memberName,
										 final String email,
										 final boolean isAdmin,
										 final boolean isPrivate) {
		return new MemberCreateRequest(memberId, password, memberName, email, isAdmin, isPrivate);
	}
}
