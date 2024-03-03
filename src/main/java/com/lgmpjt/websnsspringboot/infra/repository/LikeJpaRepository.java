package com.lgmpjt.websnsspringboot.infra.repository;

import com.lgmpjt.websnsspringboot.adapter.out.entity.LikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeJpaRepository extends JpaRepository<LikeEntity, Long> {
	LikeEntity findByUserSeqAndBoardSeq(Long userSeq, Long boardSeq);

	List<LikeEntity> findAllByUserSeq(Long userSeq);
}
