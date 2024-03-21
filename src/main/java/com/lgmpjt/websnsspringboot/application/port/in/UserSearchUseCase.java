package com.lgmpjt.websnsspringboot.application.port.in;

import com.lgmpjt.websnsspringboot.application.port.in.dto.MemberDto;
import org.springframework.transaction.annotation.Transactional;

public interface UserSearchUseCase {
	@Transactional(readOnly = true)
	MemberDto getUserByUserSeq(final Long userSeq);
}
