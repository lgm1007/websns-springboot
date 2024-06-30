package com.lgmpjt.websnsspringboot.infra.repository;

import com.lgmpjt.websnsspringboot.adapter.out.persistence.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FollowJpaRepository extends JpaRepository<Follow, Long> {
	Follow findByFromFollowAndToFollow(Long fromFollow, Long toFollow);

	List<Follow> findAllByFromFollow(Long fromFollow);

	List<Follow> findAllByToFollow(Long toFollow);
}
