package com.lgmpjt.websnsspringboot.user;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
	private final UserPort userPort;

	public UserService(final UserPort userPort) {
		this.userPort = userPort;
	}

	@Transactional
	public void createUser(final UserCreateDto userCreateDto) {
		final User user = new User(userCreateDto.getUserId(),
				userCreateDto.getPassword(),
				userCreateDto.getUserName(),
				userCreateDto.getUserEmail());
		userPort.save(user);
	}
}
