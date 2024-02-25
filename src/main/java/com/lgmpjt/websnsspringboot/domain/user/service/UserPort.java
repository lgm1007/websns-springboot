package com.lgmpjt.websnsspringboot.domain.user.service;

import com.lgmpjt.websnsspringboot.adapter.out.entity.User;

public interface UserPort {
	User save(final User user);

	User getUserByUserSeq(final Long userSeq);

	void delete(final User user);
}
