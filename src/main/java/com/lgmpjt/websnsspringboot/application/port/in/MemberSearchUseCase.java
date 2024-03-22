package com.lgmpjt.websnsspringboot.application.port.in;

import com.lgmpjt.websnsspringboot.application.port.in.dto.MemberDto;
import org.springframework.transaction.annotation.Transactional;

public interface MemberSearchUseCase {
	@Transactional(readOnly = true)
	MemberDto getMemberByMemberSeq(final Long memberSeq);

	@Transactional(readOnly = true)
	MemberDto getMemberByMemberId(final String memberId);
}
