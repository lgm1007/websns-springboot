package com.lgmpjt.websnsspringboot.application.port.out;

import com.lgmpjt.websnsspringboot.adapter.out.persistence.entity.Member;

public interface UserPort {
	Member save(final Member member);

	Member getUserByUserSeq(final Long userSeq);

	void delete(final Member member);
}
