package com.lgmpjt.websnsspringboot.adapter.out.persistence.repository;

import com.lgmpjt.websnsspringboot.adapter.out.persistence.entity.Board;

import java.util.List;
import java.util.Optional;

public interface BoardRepository {

	Optional<Board> findById(Long boardSeq);

	List<Board> findAllByMemberSeq(Long memberSeq);

	Board save(Board board);

	void delete(Board board);
}
