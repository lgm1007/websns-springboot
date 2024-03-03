package com.lgmpjt.websnsspringboot.adapter.out.entity;

import java.util.List;

public interface LikeRepository  {
	LikeEntity findByUserSeqAndBoardSeq(Long userSeq, Long boardSeq);

	List<LikeEntity> findAllByUserSeq(Long userSeq);

	LikeEntity save(LikeEntity like);

	void delete(LikeEntity like);
}
