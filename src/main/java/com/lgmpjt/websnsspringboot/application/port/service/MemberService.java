package com.lgmpjt.websnsspringboot.application.port.service;

import com.lgmpjt.websnsspringboot.adapter.out.persistence.entity.Member;
import com.lgmpjt.websnsspringboot.application.port.in.MemberCommandUseCase;
import com.lgmpjt.websnsspringboot.application.port.in.MemberSearchUseCase;
import com.lgmpjt.websnsspringboot.application.port.in.dto.MemberCreateDto;
import com.lgmpjt.websnsspringboot.application.port.in.dto.MemberDto;
import com.lgmpjt.websnsspringboot.application.port.out.MemberPort;
import com.lgmpjt.websnsspringboot.mapper.MemberMapper;
import com.lgmpjt.websnsspringboot.utils.SHA256;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService implements MemberSearchUseCase, MemberCommandUseCase {
	private final MemberPort memberPort;

	@Override
	public Member createMember(final MemberCreateDto memberCreateDto) {
		encryptPassword(memberCreateDto);
		final Member member = MemberMapper.INSTANCE.createDtoToMember(memberCreateDto);
		return memberPort.save(member);
	}

	private void encryptPassword(MemberCreateDto memberCreateDto) {
		try {
			memberCreateDto.setPassword(SHA256.encrypt(memberCreateDto.getPassword()));
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	@Override
	public MemberDto getMemberByMemberSeq(final Long memberSeq) {
		final Member member = memberPort.getMemberByMemberSeq(memberSeq);
		return MemberMapper.INSTANCE.toMemberDto(member);
	}

	@Override
	public MemberDto getMemberByMemberId(String memberId) {
		final Member member = memberPort.getMemberByMemberId(memberId);
		return MemberMapper.INSTANCE.toMemberDto(member);
	}

	@Override
	public void updateMember(final MemberDto memberDto) {
		final Member member = memberPort.getMemberByMemberSeq(memberDto.getMemberSeq());

		try {
			member.updateMember(
					SHA256.encrypt(memberDto.getPassword()),
					memberDto.getMemberName(),
					memberDto.getEmail()
			);
			memberPort.save(member);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	@Override
	public void deleteMember(final Long memberSeq) {
		final Member member = memberPort.getMemberByMemberSeq(memberSeq);
		memberPort.delete(member);
	}
}
