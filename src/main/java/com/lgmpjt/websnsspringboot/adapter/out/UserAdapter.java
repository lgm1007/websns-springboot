package com.lgmpjt.websnsspringboot.adapter.out;

import com.lgmpjt.websnsspringboot.adapter.out.entity.User;
import com.lgmpjt.websnsspringboot.adapter.out.entity.UserRepository;
import com.lgmpjt.websnsspringboot.domain.user.service.UserPort;
import org.springframework.stereotype.Component;

@Component
class UserAdapter implements UserPort {
	private final UserRepository userRepository;

	UserAdapter(final UserRepository userRepository) {
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
