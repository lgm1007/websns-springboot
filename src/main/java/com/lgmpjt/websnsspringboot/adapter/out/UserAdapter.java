package com.lgmpjt.websnsspringboot.adapter.out;

import com.lgmpjt.websnsspringboot.adapter.out.persistence.entity.Member;
import com.lgmpjt.websnsspringboot.adapter.out.persistence.repository.UserRepository;
import com.lgmpjt.websnsspringboot.application.port.out.UserPort;
import org.springframework.stereotype.Component;

@Component
class UserAdapter implements UserPort {
	private final UserRepository userRepository;

	UserAdapter(final UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public Member save(final Member member) {
		return userRepository.save(member);
	}

	@Override
	public Member getUserByUserSeq(final Long userSeq) {
		return userRepository.findById(userSeq).orElseThrow(() -> new UnsupportedOperationException("Not found user"));
	}

	@Override
	public void delete(final Member member) {
		userRepository.delete(member);
	}
}
