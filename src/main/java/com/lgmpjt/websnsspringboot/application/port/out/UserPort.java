package com.lgmpjt.websnsspringboot.application.port.out;

import com.lgmpjt.websnsspringboot.adapter.out.persistence.entity.User;

public interface UserPort {
	User save(final User user);

	User getUserByUserSeq(final Long userSeq);

	void delete(final User user);
}
