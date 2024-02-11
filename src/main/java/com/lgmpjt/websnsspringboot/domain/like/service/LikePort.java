package com.lgmpjt.websnsspringboot.domain.like.service;

import com.lgmpjt.websnsspringboot.domain.like.model.Likes;

public interface LikePort {
	Likes save(final Likes like);

	Likes findByUserSeqAndBoardSeq(final Long userSeq, final Long boardSeq);

	void delete(final Likes like);
}
