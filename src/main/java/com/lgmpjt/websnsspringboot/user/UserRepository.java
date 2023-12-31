package com.lgmpjt.websnsspringboot.user;

import java.util.HashMap;
import java.util.Map;

public class UserRepository {
	private Long sequence = 0L;
	private Map<Long, User> persistence = new HashMap<>();

	public void save(User user) {
		user.assignId(++sequence);
		persistence.put(user.getUserSeq(), user);
	}
}
