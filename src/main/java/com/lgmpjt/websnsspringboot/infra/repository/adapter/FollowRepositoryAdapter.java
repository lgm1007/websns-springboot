package com.lgmpjt.websnsspringboot.infra.repository.adapter;

import com.lgmpjt.websnsspringboot.adapter.out.entity.Follow;
import com.lgmpjt.websnsspringboot.adapter.out.entity.FollowRepository;
import com.lgmpjt.websnsspringboot.infra.repository.FollowJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class FollowRepositoryAdapter implements FollowRepository {

	private final FollowJpaRepository jpaRepository;

	@Override
	public Follow findByFromFollowAndToFollow(Long fromFollow, Long toFollow) {
		return jpaRepository.findByFromFollowAndToFollow(fromFollow, toFollow);
	}

	@Override
	public List<Follow> findAllByFromFollow(Long fromFollow) {
		return jpaRepository.findAllByFromFollow(fromFollow);
	}

	@Override
	public List<Follow> findAllByToFollow(Long toFollow) {
		return jpaRepository.findAllByToFollow(toFollow);
	}

	@Override
	public Follow save(Follow follow) {
		return jpaRepository.save(follow);
	}

	@Override
	public void delete(Follow follow) {
		jpaRepository.delete(follow);
	}
}
