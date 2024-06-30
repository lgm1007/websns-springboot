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

import java.util.List;

@Service
@RequiredArgsConstructor
public class FollowService implements FollowSearchUseCase, FollowCommandUseCase {

	private final FollowPort followPort;
	private final MemberPort memberPort;

	@Override
	public Follow saveFollow(final Long fromFollowMemberSeq, final Long toFollowMemberSeq) {
		Follow follow = Follow.builder()
			.fromFollow(fromFollowMemberSeq)
			.toFollow(toFollowMemberSeq)
			.build();
		return followPort.save(follow);
	}

	@Override
	public List<FollowDto> findAllFollowingByMember(final Long memberSeq) {
		return FollowMapper.INSTANCE.followToSearchDtos(followPort.findAllByFrom(memberSeq));
	}

	@Override
	public List<FollowDto> findAllFollowerByMember(final Long memberSeq) {
		return FollowMapper.INSTANCE.followToSearchDtos(followPort.findAllByTo(memberSeq));
	}

	@Override
	public void deleteFollow(final Long fromFollowMemberSeq, final Long toFollowMemberSeq) {
		final Follow follow = followPort.findByFromAndTo(fromFollowMemberSeq, toFollowMemberSeq);
		followPort.delete(follow);
	}
}
