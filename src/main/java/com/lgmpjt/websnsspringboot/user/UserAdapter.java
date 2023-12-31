package com.lgmpjt.websnsspringboot.user;

class UserAdapter implements UserPort {
	private final UserRepository userRepository;

	UserAdapter(final UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public void save(final User user) {
		userRepository.save(user);
	}
}