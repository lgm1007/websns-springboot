package com.lgmpjt.websnsspringboot.application.port.service;

import com.lgmpjt.websnsspringboot.adapter.out.persistence.entity.Member;
import com.lgmpjt.websnsspringboot.application.port.in.MemberCommandUseCase;
import com.lgmpjt.websnsspringboot.application.port.in.MemberSearchUseCase;
import com.lgmpjt.websnsspringboot.application.port.in.dto.MemberCreateDto;
import com.lgmpjt.websnsspringboot.application.port.in.dto.MemberDto;
import com.lgmpjt.websnsspringboot.application.port.out.MemberPort;
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
public class MemberService implements MemberSearchUseCase, MemberCommandUseCase {
	private final MemberPort memberPort;

	private final SHA256 sha256;

	@Transactional
	public Member createMember(final MemberCreateDto memberCreateDto) {
		encryptPassword(memberCreateDto);
		final Member member = UserMapper.INSTANCE.createDtoToUser(memberCreateDto);
		return memberPort.save(member);
	}

	private void encryptPassword(MemberCreateDto memberCreateDto) {
		try {
			memberCreateDto.setPassword(SHA256.encrypt(memberCreateDto.getPassword()));
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	@Transactional(readOnly = true)
	public MemberDto getMemberByMemberSeq(final Long memberSeq) {
		final Member member = memberPort.getMemberByMemberSeq(memberSeq);
		return UserMapper.INSTANCE.toUserSearchDto(member);
	}

	@Transactional
	public void updateMember(final MemberDto memberDto) {
		final Member member = memberPort.getMemberByMemberSeq(memberDto.getMemberSeq());
		member.setPassword(memberDto.getPassword());
		member.setMemberName(memberDto.getMemberName());
		member.setEmail(memberDto.getEmail());
		member.setLastModifiedDate(LocalDateTime.now());
		memberPort.save(member);
	}

	@Transactional
	public void deleteMember(final Long memberSeq) {
		final Member member = memberPort.getMemberByMemberSeq(memberSeq);
		memberPort.delete(member);
	}
}
