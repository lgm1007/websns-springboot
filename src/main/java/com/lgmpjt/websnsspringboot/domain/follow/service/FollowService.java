package com.lgmpjt.websnsspringboot.domain.follow.service;

import com.lgmpjt.websnsspringboot.domain.follow.data.FollowSearchDto;
import com.lgmpjt.websnsspringboot.domain.follow.model.Follow;
import com.lgmpjt.websnsspringboot.domain.user.model.User;
import com.lgmpjt.websnsspringboot.domain.user.service.UserPort;
import com.lgmpjt.websnsspringboot.mapper.follow.FollowMapper;
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
		User fromFollow = userPort.findUser(fromFollowUserSeq);
		User toFollow = userPort.findUser(toFollowUserSeq);
		Follow follow = Follow.builder()
				.from(fromFollow)
				.to(toFollow)
				.build();
		return followPort.save(follow);
	}

	@Transactional(readOnly = true)
	public List<FollowSearchDto> searchFollowingByUser(final Long userSeq) {
		return FollowMapper.INSTANCE.followToSearchDtos(followPort.findAllByFrom(userSeq));
	}

	@Transactional(readOnly = true)
	public List<FollowSearchDto> searchFollowerByUser(final Long userSeq) {
		return FollowMapper.INSTANCE.followToSearchDtos(followPort.findAllByTo(userSeq));
	}

	@Transactional
	public void deleteFollow(final Long fromFollowUserSeq, final Long toFollowUserSeq) {
		final Follow follow = followPort.findByFromAndTo(fromFollowUserSeq, toFollowUserSeq);
		followPort.delete(follow);
	}
}
