package com.lgmpjt.websnsspringboot.adapter.out.persistence.repository;

import com.lgmpjt.websnsspringboot.adapter.out.persistence.entity.LikeEntity;

import java.util.List;

public interface LikeRepository  {
	LikeEntity findByMemberSeqAndBoardSeq(Long memberSeq, Long boardSeq);

	List<LikeEntity> findAllByMemberSeq(Long memberSeq);

	LikeEntity save(LikeEntity like);

	void delete(LikeEntity like);
}
