package com.lgmpjt.websnsspringboot.application.port.service;

import com.lgmpjt.websnsspringboot.application.port.in.dto.BoardCreateDto;
import com.lgmpjt.websnsspringboot.application.port.in.dto.BoardDto;
import com.lgmpjt.websnsspringboot.adapter.out.entity.Board;
import com.lgmpjt.websnsspringboot.application.port.out.BoardPort;
import com.lgmpjt.websnsspringboot.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardService {
	private final BoardPort boardPort;

	public Board createBoard(final BoardCreateDto boardCreateDto) {
		Board board = BoardMapper.INSTANCE.createDtoToBoard(boardCreateDto);
		return boardPort.save(board);
	}

	public BoardDto getBoardByBoardSeq(final Long boardSeq) {
		Board board = boardPort.getBoardByBoardSeq(boardSeq);
		return BoardMapper.INSTANCE.boardToDto(board);
	}

	public List<BoardDto> searchBoardsByUserSeq(final Long userSeq) {
		List<Board> boards = boardPort.findAllBoardsByUserSeq(userSeq);
		return BoardMapper.INSTANCE.boardsToDtos(boards);
	}

	public void updateBoard(final BoardDto boardDto) {
		final Board board = boardPort.getBoardByBoardSeq(boardDto.getBoardSeq());
		board.setContent(boardDto.getContent());
		board.setBoardImage(boardDto.getBoardImage());
		board.setLastModifiedDate(LocalDateTime.now());
		boardPort.save(board);
	}

	public void deleteBoard(final Long boardSeq) {
		final Board board = boardPort.getBoardByBoardSeq(boardSeq);
		boardPort.delete(board);
	}
}