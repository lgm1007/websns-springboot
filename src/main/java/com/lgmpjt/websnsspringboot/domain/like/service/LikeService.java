package com.lgmpjt.websnsspringboot.domain.like.service;

import com.lgmpjt.websnsspringboot.domain.board.data.BoardDto;
import com.lgmpjt.websnsspringboot.domain.board.service.BoardPort;
import com.lgmpjt.websnsspringboot.adapter.out.entity.Likes;
import com.lgmpjt.websnsspringboot.domain.user.service.UserPort;
import com.lgmpjt.websnsspringboot.mapper.board.BoardMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class LikeService {

	private final LikePort likePort;
	private final UserPort userPort;
	private final BoardPort boardPort;

	@Transactional
	public Likes createLike(final Long userSeq, final Long boardSeq) {

		Likes like = Likes.builder()
				.user(userPort.getUserByUserSeq(userSeq))
				.board(boardPort.getBoardByBoardSeq(boardSeq))
				.createdDate(LocalDateTime.now())
				.build();
		return likePort.save(like);
	}

	@Transactional
	public void deleteLike(final Long userSeq, final Long boardSeq) {
		Likes like = likePort.findByUserSeqAndBoardSeq(userSeq, boardSeq);
		likePort.delete(like);
	}

	@Transactional(readOnly = true)
	public List<BoardDto> getLikeBoardByUser(final Long userSeq) {
		return likePort.findAllByUserSeq(userSeq).stream()
				.map(like -> BoardMapper.INSTANCE.boardToDto(like.getBoard()))
				.toList();
	}
}
