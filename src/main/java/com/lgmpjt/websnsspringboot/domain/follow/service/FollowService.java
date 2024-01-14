package com.lgmpjt.websnsspringboot.domain.follow.service;

import com.lgmpjt.websnsspringboot.domain.follow.data.FollowCreateDto;
import com.lgmpjt.websnsspringboot.domain.follow.model.Follow;
import com.lgmpjt.websnsspringboot.mapper.follow.FollowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FollowService {

	private final FollowPort followPort;

	@Transactional
	public Follow saveFollow(final Long fromFollowUserSeq, final Long toFollowUserSeq) {
		final FollowCreateDto followDto = new FollowCreateDto(fromFollowUserSeq, toFollowUserSeq);
		final Follow follow = FollowMapper.INSTANCE.createDtoToFollow(followDto);
		return followPort.save(follow);
	}
}
