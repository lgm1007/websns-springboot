package com.lgmpjt.websnsspringboot.user.service;

import com.lgmpjt.websnsspringboot.mapper.user.UserMapper;
import com.lgmpjt.websnsspringboot.user.data.UserCreateDto;
import com.lgmpjt.websnsspringboot.user.data.UserSearchUpdateDto;
import com.lgmpjt.websnsspringboot.user.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

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

	@Transactional(readOnly = true)
	public UserSearchUpdateDto findUser(final Long userSeq) {
		final User user = userPort.findUser(userSeq);
		return UserMapper.INSTANCE.toUserSearchDto(user);
	}

	@Transactional
	public void updateUser(final UserSearchUpdateDto userDto) {
		final User user = userPort.findUser(userDto.getUserSeq());
		user.setPassword(userDto.getPassword());
		user.setUserName(userDto.getUserName());
		user.setUserEmail(userDto.getUserEmail());
		user.setLastModifiedDate(LocalDateTime.now());
		userPort.save(user);
	}

	@Transactional
	public void withdrawUser(final Long userSeq) {
		final User user = userPort.findUser(userSeq);
		userPort.delete(user);
	}
}
