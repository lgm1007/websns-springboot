package com.lgmpjt.websnsspringboot.application.port.in;

import com.lgmpjt.websnsspringboot.adapter.out.persistence.entity.User;
import com.lgmpjt.websnsspringboot.application.port.in.dto.UserCreateDto;
import com.lgmpjt.websnsspringboot.application.port.in.dto.UserDto;
import org.springframework.transaction.annotation.Transactional;

public interface UserCommandUseCase {
	@Transactional
	User createUser(final UserCreateDto userCreateDto);

	@Transactional
	void updateUser(final UserDto userDto);

	@Transactional
	void deleteUser(final Long userSeq);
}
