package com.lgmpjt.websnsspringboot.adapter.out.entity;

import java.util.List;

public interface FollowRepository {
	Follow findByFromFollowAndToFollow(Long fromFollow, Long toFollow);

	List<Follow> findAllByFromFollow(Long fromFollow);

	List<Follow> findAllByToFollow(Long toFollow);
}
