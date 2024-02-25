package com.lgmpjt.websnsspringboot.infra.repository;

import com.lgmpjt.websnsspringboot.adapter.out.entity.Likes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeJpaRepository extends JpaRepository<Likes, Long> {
	Likes findByUserSeqAndBoardSeq(Long userSeq, Long boardSeq);

	List<Likes> findAllByUserSeq(Long userSeq);
}
