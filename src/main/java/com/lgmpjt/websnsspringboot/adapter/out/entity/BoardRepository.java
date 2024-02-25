package com.lgmpjt.websnsspringboot.adapter.out.entity;

import com.lgmpjt.websnsspringboot.adapter.out.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
	List<Board> findAllByUserSeq(Long userSeq);
}
