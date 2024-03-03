package com.lgmpjt.websnsspringboot.infra.repository.adapter;

import com.lgmpjt.websnsspringboot.adapter.out.entity.LikeRepository;
import com.lgmpjt.websnsspringboot.adapter.out.entity.Likes;
import com.lgmpjt.websnsspringboot.infra.repository.LikeJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class LikeRepositoryAdapter implements LikeRepository {

	private final LikeJpaRepository jpaRepository;

	@Override
	public Likes findByUserSeqAndBoardSeq(Long userSeq, Long boardSeq) {
		return jpaRepository.findByUserSeqAndBoardSeq(userSeq, boardSeq);
	}

	@Override
	public List<Likes> findAllByUserSeq(Long userSeq) {
		return jpaRepository.findAllByUserSeq(userSeq);
	}

	@Override
	public Likes save(Likes like) {
		return jpaRepository.save(like);
	}

	@Override
	public void delete(Likes like) {
		jpaRepository.delete(like);
	}
}
