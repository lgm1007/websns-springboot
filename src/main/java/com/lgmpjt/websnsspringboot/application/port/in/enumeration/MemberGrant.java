package com.lgmpjt.websnsspringboot.application.port.in.enumeration;

public enum MemberGrant {
	USER,
	ADMIN;

	public boolean isAdmin() {
		return this == ADMIN;
	}
}
