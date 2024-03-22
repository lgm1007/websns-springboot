package com.lgmpjt.websnsspringboot.application.port.out;

import com.lgmpjt.websnsspringboot.adapter.out.persistence.entity.Member;

public interface MemberPort {
	Member save(final Member member);

	Member getMemberByMemberSeq(final Long memberSeq);

	Member getMemberByMemberId(final String memberId);

	void delete(final Member member);
}
