package com.lgmpjt.websnsspringboot.adapter.out.persistence.repository;

import com.lgmpjt.websnsspringboot.adapter.out.persistence.entity.Follow;

import java.util.List;

public interface FollowRepository {
	Follow findByFromFollowAndToFollow(Long fromFollow, Long toFollow);

	List<Follow> findAllByFromFollow(Long fromFollow);

	List<Follow> findAllByToFollow(Long toFollow);

	Follow save(Follow follow);

	void delete(Follow follow);
}
