package com.lgmpjt.websnsspringboot.application.port.in;

import com.lgmpjt.websnsspringboot.adapter.out.persistence.entity.LikeEntity;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface LikeCommandUseCase {

	LikeEntity createLike(final Long memberSeq, final Long boardSeq);

	void deleteLike(final Long memberSeq, final Long boardSeq);
}
