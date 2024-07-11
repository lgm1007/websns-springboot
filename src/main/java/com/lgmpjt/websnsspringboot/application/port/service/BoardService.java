package com.lgmpjt.websnsspringboot.application.port.service;

import com.lgmpjt.websnsspringboot.adapter.out.persistence.entity.Board;
import com.lgmpjt.websnsspringboot.application.port.in.BoardCommandUseCase;
import com.lgmpjt.websnsspringboot.application.port.in.BoardSearchUseCase;
import com.lgmpjt.websnsspringboot.application.port.in.dto.BoardDto;
import com.lgmpjt.websnsspringboot.application.port.out.BoardPort;
import com.lgmpjt.websnsspringboot.application.port.service.dto.BoardServiceDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardService implements BoardSearchUseCase, BoardCommandUseCase {
	private final BoardPort boardPort;

	@Override
	public Board createBoard(final BoardServiceDto boardServiceDto) {
		Board board = Board.from(boardServiceDto);
		return boardPort.save(board);
	}

	@Override
	public BoardDto getBoardByBoardSeq(final Long boardSeq) {
		Board board = boardPort.getBoardByBoardSeq(boardSeq);
		return BoardDto.from(board);
	}

	@Override
	public List<BoardDto> findAllBoardsByMemberSeq(final Long memberSeq) {
		List<Board> boards = boardPort.findAllBoardsByMemberSeq(memberSeq);
		return boards.stream()
			.map(BoardDto::from)
			.toList();
	}

	@Override
	public void updateBoard(final BoardDto boardDto) {
		final Board board = boardPort.getBoardByBoardSeq(boardDto.getBoardSeq());
		board.updateBoard(
				boardDto.getContent(),
				boardDto.getBoardImage()
		);
		boardPort.save(board);
	}

	@Override
	public void deleteBoard(final Long boardSeq) {
		final Board board = boardPort.getBoardByBoardSeq(boardSeq);
		boardPort.delete(board);
	}
}
