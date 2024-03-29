package com.lgmpjt.websnsspringboot.infra.repository;

import com.lgmpjt.websnsspringboot.adapter.out.persistence.entity.Board;
import com.lgmpjt.websnsspringboot.adapter.out.persistence.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BoardRepositoryImpl implements BoardRepository {

	private final BoardJpaRepository jpaRepository;

	@Override
	public Optional<Board> findById(Long boardSeq) {
		return jpaRepository.findById(boardSeq);
	}

	@Override
	public List<Board> findAllByMemberSeq(Long memberSeq) {
		return jpaRepository.findAllByMemberSeq(memberSeq);
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
