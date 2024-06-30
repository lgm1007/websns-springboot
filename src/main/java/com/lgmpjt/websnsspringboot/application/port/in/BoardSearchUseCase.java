package com.lgmpjt.websnsspringboot.application.port.in;

import com.lgmpjt.websnsspringboot.application.port.in.dto.BoardDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface BoardSearchUseCase {

	BoardDto getBoardByBoardSeq(final Long boardSeq);

	List<BoardDto> findAllBoardsByMemberSeq(final Long memberSeq);
}
