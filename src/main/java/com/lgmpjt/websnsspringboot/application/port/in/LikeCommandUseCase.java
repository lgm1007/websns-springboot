package com.lgmpjt.websnsspringboot.application.port.in;

import com.lgmpjt.websnsspringboot.adapter.out.persistence.entity.LikeEntity;
import org.springframework.transaction.annotation.Transactional;

public interface LikeCommandUseCase {
	@Transactional
	LikeEntity createLike(final Long memberSeq, final Long boardSeq);

	@Transactional
	void deleteLike(final Long memberSeq, final Long boardSeq);
}
