package com.lgmpjt.websnsspringboot.domain.follow.service;

import com.lgmpjt.websnsspringboot.domain.follow.model.Follow;
import com.lgmpjt.websnsspringboot.domain.follow.repository.FollowRepository;
import org.springframework.stereotype.Component;

import java.util.List;

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

	@Override
	public List<Follow> findAllByFrom(final Long fromFollowUserSeq) {
		return followRepository.findAllByFromFollow(fromFollowUserSeq);
	}

	@Override
	public Follow findByFromAndTo(final Long fromFollowUserSeq, final Long toFollowUserSeq) {
		return followRepository.findByFromFollowAndToFollow(fromFollowUserSeq, toFollowUserSeq);
	}

	@Override
	public void delete(final Follow follow) {
		followRepository.delete(follow);
	}

}
