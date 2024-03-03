package com.lgmpjt.websnsspringboot.infra.repository;

import com.lgmpjt.websnsspringboot.adapter.out.persistence.entity.User;
import com.lgmpjt.websnsspringboot.adapter.out.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

	private final UserJpaRepository jpaRepository;

	@Override
	public Optional<User> findById(Long userSeq) {
		return jpaRepository.findById(userSeq);
	}

	@Override
	public User save(User user) {
		return jpaRepository.save(user);
	}

	@Override
	public void delete(User user) {
		jpaRepository.delete(user);
	}
}
