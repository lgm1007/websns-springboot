package com.lgmpjt.websnsspringboot.infra.repository;

import com.lgmpjt.websnsspringboot.adapter.out.persistence.entity.LikeEntity;
import com.lgmpjt.websnsspringboot.adapter.out.persistence.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class LikeRepositoryImpl implements LikeRepository {

	private final LikeJpaRepository jpaRepository;

	@Override
	public LikeEntity findByUserSeqAndBoardSeq(Long userSeq, Long boardSeq) {
		return jpaRepository.findByUserSeqAndBoardSeq(userSeq, boardSeq);
	}

	@Override
	public List<LikeEntity> findAllByUserSeq(Long userSeq) {
		return jpaRepository.findAllByUserSeq(userSeq);
	}

	@Override
	public LikeEntity save(LikeEntity like) {
		return jpaRepository.save(like);
	}

	@Override
	public void delete(LikeEntity like) {
		jpaRepository.delete(like);
	}
}
