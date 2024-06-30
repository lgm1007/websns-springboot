package com.lgmpjt.websnsspringboot.adapter.out.persistence.repository;

import com.lgmpjt.websnsspringboot.adapter.out.persistence.entity.Member;

import java.util.Optional;

public interface MemberRepository {
	Optional<Member> findById(Long memberSeq);

	Optional<Member> findByMemberId(String memberId);

	Member save(Member member);

	void delete(Member member);
}
