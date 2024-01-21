package com.lgmpjt.websnsspringboot.domain.follow.service;

import com.lgmpjt.websnsspringboot.domain.follow.model.Follow;

import java.util.List;

public interface FollowPort {
	Follow save(final Follow follow);

	List<Follow> findAllByFrom(final Long fromFollowUserSeq);

	List<Follow> findAllByTo(final Long toFollowUserSeq);

	Follow findByFromAndTo(final Long fromFollowUserSeq, final Long toFollowUserSeq);

	void delete(final Follow follow);
}
