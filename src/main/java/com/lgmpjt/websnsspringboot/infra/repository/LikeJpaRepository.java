package com.lgmpjt.websnsspringboot.infra.repository;

import com.lgmpjt.websnsspringboot.adapter.out.persistence.entity.LikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikeJpaRepository extends JpaRepository<LikeEntity, Long> {
	Optional<LikeEntity> findByMemberSeqAndBoardSeq(Long memberSeq, Long boardSeq);

	List<LikeEntity> findAllByMemberSeq(Long memberSeq);
}
