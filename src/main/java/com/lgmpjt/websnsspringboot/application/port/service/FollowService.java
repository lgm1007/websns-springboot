package com.lgmpjt.websnsspringboot.application.port.service;

import com.lgmpjt.websnsspringboot.adapter.out.persistence.entity.Follow;
import com.lgmpjt.websnsspringboot.application.port.in.FollowCommandUseCase;
import com.lgmpjt.websnsspringboot.application.port.in.FollowSearchUseCase;
import com.lgmpjt.websnsspringboot.application.port.in.dto.FollowDto;
import com.lgmpjt.websnsspringboot.application.port.out.FollowPort;
import com.lgmpjt.websnsspringboot.application.port.out.MemberPort;
import com.lgmpjt.websnsspringboot.mapper.FollowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FollowService implements FollowSearchUseCase, FollowCommandUseCase {

	private final FollowPort followPort;
	private final MemberPort memberPort;

	@Override
	@Transactional
	public Follow saveFollow(final Long fromFollowUserSeq, final Long toFollowUserSeq) {
		Follow follow = Follow.builder()
				.from(memberPort.getMemberByMemberSeq(fromFollowUserSeq))
				.to(memberPort.getMemberByMemberSeq(toFollowUserSeq))
				.build();
		return followPort.save(follow);
	}

	@Override
	@Transactional(readOnly = true)
	public List<FollowDto> findAllFollowingByUser(final Long userSeq) {
		return FollowMapper.INSTANCE.followToSearchDtos(followPort.findAllByFrom(userSeq));
	}

	@Override
	@Transactional(readOnly = true)
	public List<FollowDto> findAllFollowerByUser(final Long userSeq) {
		return FollowMapper.INSTANCE.followToSearchDtos(followPort.findAllByTo(userSeq));
	}

	@Override
	@Transactional
	public void deleteFollow(final Long fromFollowUserSeq, final Long toFollowUserSeq) {
		final Follow follow = followPort.findByFromAndTo(fromFollowUserSeq, toFollowUserSeq);
		followPort.delete(follow);
	}
}
