package com.lgmpjt.websnsspringboot.infra.repository;

import com.lgmpjt.websnsspringboot.adapter.out.persistence.entity.Member;
import com.lgmpjt.websnsspringboot.adapter.out.persistence.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepository {

	private final MemberJpaRepository jpaRepository;

	@Override
	public Optional<Member> findById(Long memberSeq) {
		return jpaRepository.findById(memberSeq);
	}

	@Override
	public Member save(Member member) {
		return jpaRepository.save(member);
	}

	@Override
	public void delete(Member member) {
		jpaRepository.delete(member);
	}
}
