package com.lgmpjt.websnsspringboot.domain.like.service;

import com.lgmpjt.websnsspringboot.adapter.out.entity.Likes;
import com.lgmpjt.websnsspringboot.adapter.out.entity.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

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

	@Override
	public List<Likes> findAllByUserSeq(Long userSeq) {
		return likeRepository.findAllByUserSeq(userSeq);
	}
}
