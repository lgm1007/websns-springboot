package com.lgmpjt.websnsspringboot.application.port.in;

import com.lgmpjt.websnsspringboot.adapter.out.persistence.entity.Member;
import com.lgmpjt.websnsspringboot.application.port.in.dto.MemberCreateDto;
import com.lgmpjt.websnsspringboot.application.port.in.dto.MemberDto;
import org.springframework.transaction.annotation.Transactional;

public interface MemberCommandUseCase {
	@Transactional
	Member createMember(final MemberCreateDto memberCreateDto);

	@Transactional
	void updateMember(final MemberDto memberDto);

	@Transactional
	void deleteMember(final Long memberSeq);
}
