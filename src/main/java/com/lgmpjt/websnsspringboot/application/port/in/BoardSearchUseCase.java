package com.lgmpjt.websnsspringboot.application.port.in;

import com.lgmpjt.websnsspringboot.application.port.in.dto.BoardDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BoardSearchUseCase {
	@Transactional(readOnly = true)
	BoardDto getBoardByBoardSeq(final Long boardSeq);

	@Transactional(readOnly = true)
	List<BoardDto> findAllBoardsByUserSeq(final Long userSeq);
}
