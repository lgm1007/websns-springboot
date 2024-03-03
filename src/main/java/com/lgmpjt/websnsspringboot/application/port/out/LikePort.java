package com.lgmpjt.websnsspringboot.application.port.out;

import com.lgmpjt.websnsspringboot.adapter.out.persistence.entity.LikeEntity;

import java.util.List;

public interface LikePort {
	LikeEntity save(final LikeEntity like);

	LikeEntity findByUserSeqAndBoardSeq(final Long userSeq, final Long boardSeq);

	void delete(final LikeEntity like);

	List<LikeEntity> findAllByUserSeq(final Long userSeq);
}
