package com.lgmpjt.websnsspringboot.application.port.in;

import com.lgmpjt.websnsspringboot.adapter.out.persistence.entity.Follow;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface FollowCommandUseCase {

	Follow saveFollow(final Long fromFollowMemberSeq, final Long toFollowMemberSeq);

	void deleteFollow(final Long fromFollowMemberSeq, final Long toFollowMemberSeq);
}
