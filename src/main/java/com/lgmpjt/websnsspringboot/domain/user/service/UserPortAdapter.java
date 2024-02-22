package com.lgmpjt.websnsspringboot.domain.user.service;

import com.lgmpjt.websnsspringboot.domain.user.model.User;
import com.lgmpjt.websnsspringboot.domain.user.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
class UserPortAdapter implements UserPort {
	private final UserRepository userRepository;

	UserPortAdapter(final UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public User save(final User user) {
		return userRepository.save(user);
	}

	@Override
	public User getUserByUserSeq(final Long userSeq) {
		return userRepository.findById(userSeq).orElseThrow(() -> new UnsupportedOperationException("Not found user"));
	}

	@Override
	public void delete(final User user) {
		userRepository.delete(user);
	}
}
