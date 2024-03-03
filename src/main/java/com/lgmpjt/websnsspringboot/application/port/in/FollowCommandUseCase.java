package com.lgmpjt.websnsspringboot.application.port.in;

import com.lgmpjt.websnsspringboot.adapter.out.persistence.entity.Follow;
import org.springframework.transaction.annotation.Transactional;

public interface FollowCommandUseCase {
	@Transactional
	Follow saveFollow(final Long fromFollowUserSeq, final Long toFollowUserSeq);

	@Transactional
	void deleteFollow(final Long fromFollowUserSeq, final Long toFollowUserSeq);
}
