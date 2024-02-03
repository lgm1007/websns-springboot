package com.lgmpjt.websnsspringboot.domain.board.service;

import com.lgmpjt.websnsspringboot.domain.board.model.Board;
import com.lgmpjt.websnsspringboot.domain.board.repository.BoardRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BoardPortAdapter implements BoardPort {
	private final BoardRepository boardRepository;

	BoardPortAdapter(final BoardRepository boardRepository) {
		this.boardRepository = boardRepository;
	}

	@Override
	public Board save(final Board board) {
		return boardRepository.save(board);
	}

	@Override
	public Board findBoard(Long boardSeq) {
		return boardRepository.findById(boardSeq).orElseThrow(() -> new UnsupportedOperationException("Not found board"));
	}

	@Override
	public List<Board> findBoardsByUserSeq(Long userSeq) {
		return boardRepository.findAllByUserSeq(userSeq);
	}

	@Override
	public void delete(Board board) {
		boardRepository.delete(board);
	}
}
