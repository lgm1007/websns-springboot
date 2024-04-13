package com.lgmpjt.websnsspringboot.application.port.in;

import com.lgmpjt.websnsspringboot.application.port.in.dto.BoardDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface LikeSearchUseCase {

	List<BoardDto> findAllLikeBoardByMember(final Long memberSeq);
}
