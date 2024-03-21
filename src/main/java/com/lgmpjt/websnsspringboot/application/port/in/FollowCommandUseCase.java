package com.lgmpjt.websnsspringboot.application.port.in;

import com.lgmpjt.websnsspringboot.adapter.out.persistence.entity.Follow;
import org.springframework.transaction.annotation.Transactional;

public interface FollowCommandUseCase {
	@Transactional
	Follow saveFollow(final Long fromFollowMemberSeq, final Long toFollowMemberSeq);

	@Transactional
	void deleteFollow(final Long fromFollowMemberSeq, final Long toFollowMemberSeq);
}
