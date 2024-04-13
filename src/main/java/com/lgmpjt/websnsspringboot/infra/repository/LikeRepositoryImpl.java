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
	public LikeEntity findByMemberSeqAndBoardSeq(Long memberSeq, Long boardSeq) {
		return jpaRepository.findByMemberSeqAndBoardSeq(memberSeq, boardSeq).orElse(null);
	}

	@Override
	public List<LikeEntity> findAllByMemberSeq(Long memberSeq) {
		return jpaRepository.findAllByMemberSeq(memberSeq);
	}

	@Override
	public LikeEntity save(LikeEntity like) {
		return jpaRepository.saveAndFlush(like);
	}

	@Override
	public void delete(LikeEntity like) {
		jpaRepository.delete(like);
	}
}
