package com.lgmpjt.websnsspringboot.infra.repository;

import com.lgmpjt.websnsspringboot.adapter.out.persistence.entity.Member;
import com.lgmpjt.websnsspringboot.adapter.out.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

	private final UserJpaRepository jpaRepository;

	@Override
	public Optional<Member> findById(Long userSeq) {
		return jpaRepository.findById(userSeq);
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
