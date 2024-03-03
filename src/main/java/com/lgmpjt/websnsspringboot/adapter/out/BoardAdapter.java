package com.lgmpjt.websnsspringboot.adapter.out;

import com.lgmpjt.websnsspringboot.adapter.out.persistence.entity.Board;
import com.lgmpjt.websnsspringboot.adapter.out.persistence.repository.BoardRepository;
import com.lgmpjt.websnsspringboot.application.port.out.BoardPort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BoardAdapter implements BoardPort {
	private final BoardRepository boardRepository;

	BoardAdapter(final BoardRepository boardRepository) {
		this.boardRepository = boardRepository;
	}

	@Override
	public Board save(final Board board) {
		return boardRepository.save(board);
	}

	@Override
	public Board getBoardByBoardSeq(Long boardSeq) {
		return boardRepository.findById(boardSeq).orElseThrow(() -> new UnsupportedOperationException("Not found board"));
	}

	@Override
	public List<Board> findAllBoardsByUserSeq(Long userSeq) {
		return boardRepository.findAllByUserSeq(userSeq);
	}

	@Override
	public void delete(Board board) {
		boardRepository.delete(board);
	}
}
