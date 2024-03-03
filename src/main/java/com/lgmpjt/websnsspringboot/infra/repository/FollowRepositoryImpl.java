package com.lgmpjt.websnsspringboot.infra.repository;

import com.lgmpjt.websnsspringboot.adapter.out.persistence.entity.Follow;
import com.lgmpjt.websnsspringboot.adapter.out.persistence.repository.FollowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class FollowRepositoryImpl implements FollowRepository {

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
