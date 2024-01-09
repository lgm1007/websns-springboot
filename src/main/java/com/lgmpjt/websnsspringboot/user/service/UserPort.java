package com.lgmpjt.websnsspringboot.user.service;

import com.lgmpjt.websnsspringboot.user.model.User;

interface UserPort {
	void save(final User user);

	User findUser(final Long userSeq);

	void delete(final User user);
}
