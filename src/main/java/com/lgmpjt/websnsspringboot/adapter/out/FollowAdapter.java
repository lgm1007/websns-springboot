package com.lgmpjt.websnsspringboot.adapter.out;

import com.lgmpjt.websnsspringboot.adapter.out.entity.Follow;
import com.lgmpjt.websnsspringboot.adapter.out.entity.FollowRepository;
import com.lgmpjt.websnsspringboot.application.port.out.FollowPort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FollowAdapter implements FollowPort {
	private final FollowRepository followRepository;

	FollowAdapter(final FollowRepository followRepository) {
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
	public List<Follow> findAllByTo(final Long toFollowUserSeq) {
		return followRepository.findAllByToFollow(toFollowUserSeq);
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
