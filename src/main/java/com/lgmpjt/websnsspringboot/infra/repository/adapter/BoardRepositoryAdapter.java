package com.lgmpjt.websnsspringboot.infra.repository.adapter;

import com.lgmpjt.websnsspringboot.adapter.out.persistence.entity.Board;
import com.lgmpjt.websnsspringboot.adapter.out.persistence.repository.BoardRepository;
import com.lgmpjt.websnsspringboot.infra.repository.BoardJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BoardRepositoryAdapter implements BoardRepository {

	private final BoardJpaRepository jpaRepository;

	@Override
	public Optional<Board> findById(Long boardSeq) {
		return jpaRepository.findById(boardSeq);
	}

	@Override
	public List<Board> findAllByUserSeq(Long userSeq) {
		return jpaRepository.findAllByUserSeq(userSeq);
	}

	@Override
	public Board save(Board board) {
		return jpaRepository.save(board);
	}

	@Override
	public void delete(Board board) {
		jpaRepository.delete(board);
	}

}
