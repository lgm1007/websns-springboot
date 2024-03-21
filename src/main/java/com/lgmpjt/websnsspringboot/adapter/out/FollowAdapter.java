package com.lgmpjt.websnsspringboot.adapter.out;

import com.lgmpjt.websnsspringboot.adapter.out.persistence.entity.Follow;
import com.lgmpjt.websnsspringboot.adapter.out.persistence.repository.FollowRepository;
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
	public List<Follow> findAllByFrom(final Long fromFollowMemberSeq) {
		return followRepository.findAllByFromFollow(fromFollowMemberSeq);
	}

	@Override
	public List<Follow> findAllByTo(final Long toFollowMemberSeq) {
		return followRepository.findAllByToFollow(toFollowMemberSeq);
	}

	@Override
	public Follow findByFromAndTo(final Long fromFollowMemberSeq, final Long toFollowMemberSeq) {
		return followRepository.findByFromFollowAndToFollow(fromFollowMemberSeq, toFollowMemberSeq);
	}

	@Override
	public void delete(final Follow follow) {
		followRepository.delete(follow);
	}

}
