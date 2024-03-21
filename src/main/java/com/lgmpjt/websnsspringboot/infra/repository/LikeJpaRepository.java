package com.lgmpjt.websnsspringboot.infra.repository;

import com.lgmpjt.websnsspringboot.adapter.out.persistence.entity.LikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeJpaRepository extends JpaRepository<LikeEntity, Long> {
	LikeEntity findByMemberSeqAndBoardSeq(Long memberSeq, Long boardSeq);

	List<LikeEntity> findAllByMemberSeq(Long memberSeq);
}
