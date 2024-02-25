package com.lgmpjt.websnsspringboot.application.port.in;

import com.lgmpjt.websnsspringboot.application.port.in.dto.UserDto;
import org.springframework.transaction.annotation.Transactional;

public interface UserSearchUseCase {
	@Transactional(readOnly = true)
	UserDto getUserByUserSeq(final Long userSeq);
}
