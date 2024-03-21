package com.lgmpjt.websnsspringboot.application.port.service;

import com.lgmpjt.websnsspringboot.adapter.out.persistence.entity.Member;
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
	public Member createUser(final UserCreateDto userCreateDto) {
		encryptPassword(userCreateDto);
		final Member member = UserMapper.INSTANCE.createDtoToUser(userCreateDto);
		return userPort.save(member);
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
		final Member member = userPort.getUserByUserSeq(userSeq);
		return UserMapper.INSTANCE.toUserSearchDto(member);
	}

	@Transactional
	public void updateUser(final UserDto userDto) {
		final Member member = userPort.getUserByUserSeq(userDto.getUserSeq());
		member.setPassword(userDto.getPassword());
		member.setMemberName(userDto.getUserName());
		member.setEmail(userDto.getUserEmail());
		member.setLastModifiedDate(LocalDateTime.now());
		userPort.save(member);
	}

	@Transactional
	public void deleteUser(final Long userSeq) {
		final Member member = userPort.getUserByUserSeq(userSeq);
		userPort.delete(member);
	}
}
