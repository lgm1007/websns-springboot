package com.lgmpjt.websnsspringboot.application.port.service;

import com.lgmpjt.websnsspringboot.adapter.out.persistence.entity.LikeEntity;
import com.lgmpjt.websnsspringboot.application.port.in.LikeCommandUseCase;
import com.lgmpjt.websnsspringboot.application.port.in.LikeSearchUseCase;
import com.lgmpjt.websnsspringboot.application.port.in.dto.BoardDto;
import com.lgmpjt.websnsspringboot.application.port.out.BoardPort;
import com.lgmpjt.websnsspringboot.application.port.out.LikePort;
import com.lgmpjt.websnsspringboot.application.port.out.MemberPort;
import com.lgmpjt.websnsspringboot.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class LikeService implements LikeSearchUseCase, LikeCommandUseCase {

	private final LikePort likePort;
	private final MemberPort memberPort;
	private final BoardPort boardPort;

	@Override
	public LikeEntity createLike(final Long memberSeq, final Long boardSeq) {

		LikeEntity like = LikeEntity.builder()
				.member(memberPort.getMemberByMemberSeq(memberSeq))
				.board(boardPort.getBoardByBoardSeq(boardSeq))
				.createdDate(LocalDateTime.now())
				.build();
		return likePort.save(like);
	}

	@Override
	public void deleteLike(final Long memberSeq, final Long boardSeq) {
		LikeEntity like = likePort.findByMemberSeqAndBoardSeq(memberSeq, boardSeq);
		if (like != null) {
			likePort.delete(like);
		}
	}

	@Override
	public List<BoardDto> findAllLikeBoardByMember(final Long memberSeq) {
		return likePort.findAllByMemberSeq(memberSeq).stream()
				.map(like -> BoardMapper.INSTANCE.boardToDto(like.getBoard()))
				.toList();
	}
}
