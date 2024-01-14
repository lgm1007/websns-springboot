package com.lgmpjt.websnsspringboot.domain.follow.service;

import com.lgmpjt.websnsspringboot.domain.follow.model.Follow;
import com.lgmpjt.websnsspringboot.domain.follow.repository.FollowRepository;
import org.springframework.stereotype.Component;

@Component
public class FollowPortAdapter implements FollowPort {
	private final FollowRepository followRepository;

	FollowPortAdapter(final FollowRepository followRepository) {
		this.followRepository = followRepository;
	}

	@Override
	public Follow save(final Follow follow) {
		return followRepository.save(follow);
	}

}
