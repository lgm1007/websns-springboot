package com.lgmpjt.websnsspringboot.domain.board.repository;

import com.lgmpjt.websnsspringboot.domain.board.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
	List<Board> findAllByUserSeq(Long userSeq);
}
