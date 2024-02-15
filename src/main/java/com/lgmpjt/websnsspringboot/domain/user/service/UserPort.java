package com.lgmpjt.websnsspringboot.domain.user.service;

import com.lgmpjt.websnsspringboot.domain.user.model.User;

public interface UserPort {
	User save(final User user);

	User findUser(final Long userSeq);

	void delete(final User user);
}
