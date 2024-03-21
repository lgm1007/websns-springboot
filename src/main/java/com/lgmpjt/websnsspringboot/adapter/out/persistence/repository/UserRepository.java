package com.lgmpjt.websnsspringboot.adapter.out.persistence.repository;

import com.lgmpjt.websnsspringboot.adapter.out.persistence.entity.Member;

import java.util.Optional;

public interface UserRepository  {
	Optional<Member> findById(Long userSeq);

	Member save(Member member);

	void delete(Member member);
}
