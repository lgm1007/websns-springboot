package com.lgmpjt.websnsspringboot.application.port.in;

import com.lgmpjt.websnsspringboot.adapter.out.persistence.entity.Member;
import com.lgmpjt.websnsspringboot.application.port.in.dto.MemberCreateDto;
import com.lgmpjt.websnsspringboot.application.port.in.dto.MemberDto;
import org.springframework.transaction.annotation.Transactional;

public interface UserCommandUseCase {
	@Transactional
	Member createUser(final MemberCreateDto memberCreateDto);

	@Transactional
	void updateUser(final MemberDto memberDto);

	@Transactional
	void deleteUser(final Long userSeq);
}
