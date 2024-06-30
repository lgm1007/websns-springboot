package com.lgmpjt.websnsspringboot.application.port.out;

import com.lgmpjt.websnsspringboot.adapter.out.persistence.entity.Follow;

import java.util.List;

public interface FollowPort {
	Follow save(final Follow follow);

	List<Follow> findAllByFrom(final Long fromFollowMemberSeq);

	List<Follow> findAllByTo(final Long toFollowMemberSeq);

	Follow findByFromAndTo(final Long fromFollowMemberSeq, final Long toFollowMemberSeq);

	void delete(final Follow follow);
}
