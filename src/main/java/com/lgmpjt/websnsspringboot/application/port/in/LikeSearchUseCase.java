package com.lgmpjt.websnsspringboot.application.port.in;

import com.lgmpjt.websnsspringboot.application.port.in.dto.BoardDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface LikeSearchUseCase {
	@Transactional(readOnly = true)
	List<BoardDto> getLikeBoardByMember(final Long memberSeq);
}
