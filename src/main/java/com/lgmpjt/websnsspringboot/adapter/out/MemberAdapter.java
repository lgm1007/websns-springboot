package com.lgmpjt.websnsspringboot.adapter.out;

import com.lgmpjt.websnsspringboot.adapter.out.persistence.entity.Member;
import com.lgmpjt.websnsspringboot.adapter.out.persistence.repository.MemberRepository;
import com.lgmpjt.websnsspringboot.application.port.out.MemberPort;
import org.springframework.stereotype.Component;

@Component
class MemberAdapter implements MemberPort {
	private final MemberRepository memberRepository;

	MemberAdapter(final MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	@Override
	public Member save(final Member member) {
		return memberRepository.save(member);
	}

	@Override
	public Member getMemberByMemberSeq(final Long memberSeq) {
		return memberRepository.findById(memberSeq).orElseThrow(() -> new UnsupportedOperationException("Not found member"));
	}

	@Override
	public void delete(final Member member) {
		memberRepository.delete(member);
	}
}
