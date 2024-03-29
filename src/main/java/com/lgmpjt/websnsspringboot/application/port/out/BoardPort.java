package com.lgmpjt.websnsspringboot.application.port.out;

import com.lgmpjt.websnsspringboot.adapter.out.persistence.entity.Board;

import java.util.List;

public interface BoardPort {
	Board save(final Board board);

	Board getBoardByBoardSeq(final Long boardSeq);

	List<Board> findAllBoardsByMemberSeq(final Long memberSeq);

	void delete(final Board board);

}
