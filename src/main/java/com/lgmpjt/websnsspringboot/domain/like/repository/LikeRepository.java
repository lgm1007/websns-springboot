package com.lgmpjt.websnsspringboot.domain.like.repository;

import com.lgmpjt.websnsspringboot.domain.like.model.Likes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Likes, Long> {
	Likes findByUserSeqAndBoardSeq(Long userSeq, Long boardSeq);
}
