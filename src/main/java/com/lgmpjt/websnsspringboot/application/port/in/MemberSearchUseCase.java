package com.lgmpjt.websnsspringboot.application.port.in;

import com.lgmpjt.websnsspringboot.application.port.in.dto.MemberDto;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface MemberSearchUseCase {

	MemberDto getMemberByMemberSeq(final Long memberSeq);

	MemberDto getMemberByMemberId(final String memberId);
}
