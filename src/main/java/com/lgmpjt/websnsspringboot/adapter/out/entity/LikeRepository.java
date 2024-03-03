package com.lgmpjt.websnsspringboot.adapter.out.entity;

import java.util.List;

public interface LikeRepository  {
	Likes findByUserSeqAndBoardSeq(Long userSeq, Long boardSeq);

	List<Likes> findAllByUserSeq(Long userSeq);

	Likes save(Likes like);

	void delete(Likes like);
}
