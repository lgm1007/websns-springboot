package com.lgmpjt.websnsspringboot.application.port.out;

import com.lgmpjt.websnsspringboot.adapter.out.persistence.entity.LikeEntity;

import java.util.List;

public interface LikePort {
	LikeEntity save(final LikeEntity like);

	LikeEntity findByMemberSeqAndBoardSeq(final Long memberSeq, final Long boardSeq);

	void delete(final LikeEntity like);

	List<LikeEntity> findAllByMemberSeq(final Long memberSeq);
}
