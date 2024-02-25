package com.lgmpjt.websnsspringboot.application.port.in;

import com.lgmpjt.websnsspringboot.adapter.out.entity.Likes;
import org.springframework.transaction.annotation.Transactional;

public interface LikeCommandUseCase {
	@Transactional
	Likes createLike(final Long userSeq, final Long boardSeq);

	@Transactional
	void deleteLike(final Long userSeq, final Long boardSeq);
}
