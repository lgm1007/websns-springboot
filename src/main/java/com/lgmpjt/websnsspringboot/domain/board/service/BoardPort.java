package com.lgmpjt.websnsspringboot.domain.board.service;

import com.lgmpjt.websnsspringboot.domain.board.model.Board;

import java.util.List;

public interface BoardPort {
	Board save(final Board board);

	Board getBoardByBoardSeq(final Long boardSeq);

	List<Board> findAllBoardsByUserSeq(final Long userSeq);

	void delete(final Board board);

}
