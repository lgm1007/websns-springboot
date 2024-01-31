package com.lgmpjt.websnsspringboot.domain.user.service;

import com.lgmpjt.websnsspringboot.domain.user.data.UserSearchUpdateDto;
import com.lgmpjt.websnsspringboot.domain.user.model.User;
import com.lgmpjt.websnsspringboot.encryption.service.SHA256;
import com.lgmpjt.websnsspringboot.mapper.user.UserMapper;
import com.lgmpjt.websnsspringboot.domain.user.data.UserCreateDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
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
			userCreateDto.setPassword(sha256.encrypt(userCreateDto.getPassword()));
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	@Transactional(readOnly = true)
	public UserSearchUpdateDto searchUser(final Long userSeq) {
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
