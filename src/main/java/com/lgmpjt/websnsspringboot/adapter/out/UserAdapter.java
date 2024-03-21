package com.lgmpjt.websnsspringboot.adapter.out;

import com.lgmpjt.websnsspringboot.adapter.out.persistence.entity.Member;
import com.lgmpjt.websnsspringboot.adapter.out.persistence.repository.MemberRepository;
import com.lgmpjt.websnsspringboot.application.port.out.UserPort;
import org.springframework.stereotype.Component;

@Component
class UserAdapter implements UserPort {
	private final MemberRepository memberRepository;

	UserAdapter(final MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	@Override
	public Member save(final Member member) {
		return memberRepository.save(member);
	}

	@Override
	public Member getUserByUserSeq(final Long userSeq) {
		return memberRepository.findById(userSeq).orElseThrow(() -> new UnsupportedOperationException("Not found user"));
	}

	@Override
	public void delete(final Member member) {
		memberRepository.delete(member);
	}
}
