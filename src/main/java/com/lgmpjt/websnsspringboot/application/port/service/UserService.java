package com.lgmpjt.websnsspringboot.application.port.service;

import com.lgmpjt.websnsspringboot.adapter.out.persistence.entity.User;
import com.lgmpjt.websnsspringboot.application.port.in.UserCommandUseCase;
import com.lgmpjt.websnsspringboot.application.port.in.UserSearchUseCase;
import com.lgmpjt.websnsspringboot.application.port.in.dto.UserCreateDto;
import com.lgmpjt.websnsspringboot.application.port.in.dto.UserDto;
import com.lgmpjt.websnsspringboot.application.port.out.UserPort;
import com.lgmpjt.websnsspringboot.mapper.UserMapper;
import com.lgmpjt.websnsspringboot.utils.SHA256;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService implements UserSearchUseCase, UserCommandUseCase {
	private final UserPort userPort;

	private final SHA256 sha256;

	@Transactional
	public User createUser(final UserCreateDto userCreateDto) {
		encryptPassword(userCreateDto);
		final User user = UserMapper.INSTANCE.createDtoToUser(userCreateDto);
		return userPort.save(user);
	}

	private void encryptPassword(UserCreateDto userCreateDto) {
		try {
			userCreateDto.setPassword(SHA256.encrypt(userCreateDto.getPassword()));
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	@Transactional(readOnly = true)
	public UserDto getUserByUserSeq(final Long userSeq) {
		final User user = userPort.getUserByUserSeq(userSeq);
		return UserMapper.INSTANCE.toUserSearchDto(user);
	}

	@Transactional
	public void updateUser(final UserDto userDto) {
		final User user = userPort.getUserByUserSeq(userDto.getUserSeq());
		user.setPassword(userDto.getPassword());
		user.setUserName(userDto.getUserName());
		user.setUserEmail(userDto.getUserEmail());
		user.setLastModifiedDate(LocalDateTime.now());
		userPort.save(user);
	}

	@Transactional
	public void deleteUser(final Long userSeq) {
		final User user = userPort.getUserByUserSeq(userSeq);
		userPort.delete(user);
	}
}
