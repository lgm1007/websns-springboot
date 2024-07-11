package com.lgmpjt.websnsspringboot.application.port.in;

import com.lgmpjt.websnsspringboot.adapter.out.persistence.entity.Member;
import com.lgmpjt.websnsspringboot.application.port.in.dto.MemberDto;
import com.lgmpjt.websnsspringboot.application.port.service.dto.MemberServiceDto;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface MemberCommandUseCase {

	Member createMember(final MemberServiceDto memberServiceDto);

	void updateMember(final MemberDto memberDto);

	void deleteMember(final Long memberSeq);
}
