package com.lgmpjt.websnsspringboot.domain.like.service;

import com.lgmpjt.websnsspringboot.adapter.out.entity.Likes;

import java.util.List;

public interface LikePort {
	Likes save(final Likes like);

	Likes findByUserSeqAndBoardSeq(final Long userSeq, final Long boardSeq);

	void delete(final Likes like);

	List<Likes> findAllByUserSeq(final Long userSeq);
}
