package com.lgmpjt.websnsspringboot.infra.repository.adapter;

import com.lgmpjt.websnsspringboot.adapter.out.entity.User;
import com.lgmpjt.websnsspringboot.adapter.out.entity.UserRepository;
import com.lgmpjt.websnsspringboot.infra.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserRepository {

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
