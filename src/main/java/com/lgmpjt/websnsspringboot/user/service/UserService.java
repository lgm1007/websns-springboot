package com.lgmpjt.websnsspringboot.user.service;

import com.lgmpjt.websnsspringboot.mapper.user.UserMapper;
import com.lgmpjt.websnsspringboot.user.data.UserCreateDto;
import com.lgmpjt.websnsspringboot.user.data.UserSearchDto;
import com.lgmpjt.websnsspringboot.user.model.User;
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
		final User user = UserMapper.INSTANCE.createDtoToUser(userCreateDto);
		userPort.save(user);
	}

	public UserSearchDto findUser(final Long userSeq) {
		final User user = userPort.findUser(userSeq);
		return UserMapper.INSTANCE.toUserSearchDto(user);
	}
}