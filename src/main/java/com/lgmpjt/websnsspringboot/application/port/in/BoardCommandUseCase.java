package com.lgmpjt.websnsspringboot.application.port.in;

import com.lgmpjt.websnsspringboot.adapter.out.entity.Board;
import com.lgmpjt.websnsspringboot.application.port.in.dto.BoardCreateDto;
import com.lgmpjt.websnsspringboot.application.port.in.dto.BoardDto;
import org.springframework.transaction.annotation.Transactional;

public interface BoardCommandUseCase {
	@Transactional
	Board createBoard(final BoardCreateDto boardCreateDto);

	@Transactional
	void updateBoard(final BoardDto boardDto);

	@Transactional
	void deleteBoard(final Long boardSeq);
}
