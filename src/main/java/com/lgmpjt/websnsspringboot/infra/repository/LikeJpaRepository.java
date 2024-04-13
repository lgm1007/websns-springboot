package com.lgmpjt.websnsspringboot.infra.repository;

import com.lgmpjt.websnsspringboot.adapter.out.persistence.entity.LikeEntity;
import com.lgmpjt.websnsspringboot.adapter.out.persistence.entity.id.LikeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LikeJpaRepository extends JpaRepository<LikeEntity, LikeId> {
	@Query("SELECT lk FROM LikeEntity lk WHERE lk.likeId.memberSeq = :memberSeq AND lk.likeId.boardSeq = :boardSeq")
	Optional<LikeEntity> findByMemberSeqAndBoardSeq(Long memberSeq, Long boardSeq);

	@Query("SELECT lk FROM LikeEntity lk WHERE lk.likeId.memberSeq = :memberSeq")
	List<LikeEntity> findAllByMemberSeq(Long memberSeq);
}
