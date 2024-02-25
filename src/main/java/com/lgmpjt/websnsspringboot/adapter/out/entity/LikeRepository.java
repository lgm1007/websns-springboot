package com.lgmpjt.websnsspringboot.adapter.out.entity;

import com.lgmpjt.websnsspringboot.adapter.out.entity.Likes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeRepository extends JpaRepository<Likes, Long> {
	Likes findByUserSeqAndBoardSeq(Long userSeq, Long boardSeq);

	List<Likes> findAllByUserSeq(Long userSeq);
}
