package com.lgmpjt.websnsspringboot.adapter.out.entity;

import java.util.List;
import java.util.Optional;

public interface BoardRepository {

	Optional<Board> findById(Long boardSeq);

	List<Board> findAllByUserSeq(Long userSeq);

	Board save(Board board);

	void delete(Board board);
}
