package com.lgmpjt.websnsspringboot.domain.follow.service;

import com.lgmpjt.websnsspringboot.application.port.in.dto.FollowDto;
import com.lgmpjt.websnsspringboot.adapter.out.entity.Follow;
import com.lgmpjt.websnsspringboot.application.port.out.FollowPort;
import com.lgmpjt.websnsspringboot.application.port.out.UserPort;
import com.lgmpjt.websnsspringboot.mapper.FollowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FollowService {

	private final FollowPort followPort;
	private final UserPort userPort;

	@Transactional
	public Follow saveFollow(final Long fromFollowUserSeq, final Long toFollowUserSeq) {
		Follow follow = Follow.builder()
				.from(userPort.getUserByUserSeq(fromFollowUserSeq))
				.to(userPort.getUserByUserSeq(toFollowUserSeq))
				.build();
		return followPort.save(follow);
	}

	@Transactional(readOnly = true)
	public List<FollowDto> findAllFollowingByUser(final Long userSeq) {
		return FollowMapper.INSTANCE.followToSearchDtos(followPort.findAllByFrom(userSeq));
	}

	@Transactional(readOnly = true)
	public List<FollowDto> findAllFollowerByUser(final Long userSeq) {
		return FollowMapper.INSTANCE.followToSearchDtos(followPort.findAllByTo(userSeq));
	}

	@Transactional
	public void deleteFollow(final Long fromFollowUserSeq, final Long toFollowUserSeq) {
		final Follow follow = followPort.findByFromAndTo(fromFollowUserSeq, toFollowUserSeq);
		followPort.delete(follow);
	}
}
