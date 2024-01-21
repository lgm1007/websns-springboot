package com.lgmpjt.websnsspringboot.domain.follow.repository;

import com.lgmpjt.websnsspringboot.domain.follow.model.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow, Long> {
	Follow findByFromFollowAndToFollow(Long fromFollow, Long toFollow);

	List<Follow> findAllByFromFollow(Long fromFollow);

	List<Follow> findAllByToFollow(Long toFollow);
}
