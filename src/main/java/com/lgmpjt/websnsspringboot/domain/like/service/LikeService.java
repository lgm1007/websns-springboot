package com.lgmpjt.websnsspringboot.domain.like.service;

import com.lgmpjt.websnsspringboot.domain.board.data.BoardDto;
import com.lgmpjt.websnsspringboot.domain.like.data.LikeDto;
import com.lgmpjt.websnsspringboot.domain.like.model.Likes;
import com.lgmpjt.websnsspringboot.mapper.like.LikeMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class LikeService {

	private final LikePort likePort;

	@Transactional
	public Likes createLike(final LikeDto likeDto) {
		Likes like = LikeMapper.INSTANCE.createDtoToLike(likeDto);
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
				.map(like -> {
					LikeDto dto = LikeMapper.INSTANCE.likeToDto(like);
					return dto.getBoard();
				})
				.toList();
	}
}
