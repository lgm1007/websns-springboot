package com.lgmpjt.websnsspringboot.application.port.service;

import com.lgmpjt.websnsspringboot.adapter.out.entity.LikeEntity;
import com.lgmpjt.websnsspringboot.application.port.in.LikeCommandUseCase;
import com.lgmpjt.websnsspringboot.application.port.in.LikeSearchUseCase;
import com.lgmpjt.websnsspringboot.application.port.in.dto.BoardDto;
import com.lgmpjt.websnsspringboot.application.port.out.BoardPort;
import com.lgmpjt.websnsspringboot.application.port.out.LikePort;
import com.lgmpjt.websnsspringboot.application.port.out.UserPort;
import com.lgmpjt.websnsspringboot.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class LikeService implements LikeSearchUseCase, LikeCommandUseCase {

	private final LikePort likePort;
	private final UserPort userPort;
	private final BoardPort boardPort;

	@Override
	@Transactional
	public LikeEntity createLike(final Long userSeq, final Long boardSeq) {

		LikeEntity like = LikeEntity.builder()
				.user(userPort.getUserByUserSeq(userSeq))
				.board(boardPort.getBoardByBoardSeq(boardSeq))
				.createdDate(LocalDateTime.now())
				.build();
		return likePort.save(like);
	}

	@Override
	@Transactional
	public void deleteLike(final Long userSeq, final Long boardSeq) {
		LikeEntity like = likePort.findByUserSeqAndBoardSeq(userSeq, boardSeq);
		likePort.delete(like);
	}

	@Override
	@Transactional(readOnly = true)
	public List<BoardDto> getLikeBoardByUser(final Long userSeq) {
		return likePort.findAllByUserSeq(userSeq).stream()
				.map(like -> BoardMapper.INSTANCE.boardToDto(like.getBoard()))
				.toList();
	}
}
