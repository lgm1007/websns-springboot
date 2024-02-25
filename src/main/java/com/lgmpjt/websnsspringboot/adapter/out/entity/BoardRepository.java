package com.lgmpjt.websnsspringboot.adapter.out.entity;

import java.util.List;

public interface BoardRepository {
	List<Board> findAllByUserSeq(Long userSeq);
}
