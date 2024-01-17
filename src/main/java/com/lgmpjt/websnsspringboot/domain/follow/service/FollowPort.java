package com.lgmpjt.websnsspringboot.domain.follow.service;

import com.lgmpjt.websnsspringboot.domain.follow.model.Follow;

public interface FollowPort {
	Follow save(final Follow follow);

	Follow findByFromAndTo(final Long fromFollowUserSeq, final Long toFollowUserSeq);

	void delete(final Follow follow);
}
