package com.lgmpjt.websnsspringboot.domain.like.service;

import com.lgmpjt.websnsspringboot.domain.like.model.Likes;
import com.lgmpjt.websnsspringboot.domain.like.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class LikePortAdapter implements LikePort {

	private final LikeRepository likeRepository;

	@Override
	public Likes save(Likes like) {
		return likeRepository.save(like);
	}

	@Override
	public Likes findByUserSeqAndBoardSeq(Long userSeq, Long boardSeq) {
		return likeRepository.findByUserSeqAndBoardSeq(userSeq, boardSeq);
	}

	@Override
	public void delete(Likes like) {
		likeRepository.delete(like);
	}
}
