package com.lgmpjt.websnsspringboot.domain.follow.repository;

import com.lgmpjt.websnsspringboot.domain.follow.model.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRepository extends JpaRepository<Follow, Long> {
	Follow findByFromFollowAndToFollow(Long fromFollow, Long toFollow);
}
