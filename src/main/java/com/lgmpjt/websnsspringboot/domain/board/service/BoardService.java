package com.lgmpjt.websnsspringboot.domain.board.service;

import com.lgmpjt.websnsspringboot.domain.board.data.BoardCreateDto;
import com.lgmpjt.websnsspringboot.domain.board.model.Board;
import com.lgmpjt.websnsspringboot.mapper.board.BoardMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardService {
	private final BoardPort boardPort;

	public Board createBoard(final BoardCreateDto boardCreateDto) {
		Board board = BoardMapper.INSTANCE.createDtoToBoard(boardCreateDto);
		return boardPort.save(board);
	}
}
