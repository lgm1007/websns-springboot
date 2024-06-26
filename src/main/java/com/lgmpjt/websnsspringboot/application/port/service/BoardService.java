package com.lgmpjt.websnsspringboot.application.port.service;

import com.lgmpjt.websnsspringboot.adapter.in.rest.request.BoardCreateRequest;
import com.lgmpjt.websnsspringboot.adapter.out.persistence.entity.Board;
import com.lgmpjt.websnsspringboot.application.port.in.BoardCommandUseCase;
import com.lgmpjt.websnsspringboot.application.port.in.BoardSearchUseCase;
import com.lgmpjt.websnsspringboot.application.port.in.dto.BoardDto;
import com.lgmpjt.websnsspringboot.application.port.out.BoardPort;
import com.lgmpjt.websnsspringboot.mapper.BoardMapper;
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
	public Board createBoard(final BoardCreateRequest boardCreateRequest) {
		Board board = BoardMapper.INSTANCE.createRequestToBoard(boardCreateRequest);
		return boardPort.save(board);
	}

	@Override
	public BoardDto getBoardByBoardSeq(final Long boardSeq) {
		Board board = boardPort.getBoardByBoardSeq(boardSeq);
		return BoardMapper.INSTANCE.boardToDto(board);
	}

	@Override
	public List<BoardDto> findAllBoardsByMemberSeq(final Long memberSeq) {
		List<Board> boards = boardPort.findAllBoardsByMemberSeq(memberSeq);
		return BoardMapper.INSTANCE.boardsToDtos(boards);
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
