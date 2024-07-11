package com.lgmpjt.websnsspringboot.application.port.in;

import com.lgmpjt.websnsspringboot.adapter.out.persistence.entity.Board;
import com.lgmpjt.websnsspringboot.application.port.in.dto.BoardDto;
import com.lgmpjt.websnsspringboot.application.port.service.dto.BoardServiceDto;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface BoardCommandUseCase {

	Board createBoard(final BoardServiceDto boardCreateRequest);

	void updateBoard(final BoardDto boardDto);

	void deleteBoard(final Long boardSeq);
}
