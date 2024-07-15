package com.lgmpjt.websnsspringboot.application.port.service;

import com.lgmpjt.websnsspringboot.adapter.out.persistence.entity.LikeEntity;
import com.lgmpjt.websnsspringboot.application.port.in.LikeCommandUseCase;
import com.lgmpjt.websnsspringboot.application.port.in.LikeSearchUseCase;
import com.lgmpjt.websnsspringboot.application.port.out.LikePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class LikeService implements LikeSearchUseCase, LikeCommandUseCase {

	private final LikePort likePort;

	@Override
	public LikeEntity createLike(final Long memberSeq, final Long boardSeq) {
		LikeEntity like = LikeEntity.of(memberSeq, boardSeq);
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
	public List<Long> findAllLikeBoardSeqByMemberSeq(final Long memberSeq) {
		return likePort.findAllByMemberSeq(memberSeq).stream()
			.map(like -> like.getLikeId().getBoardSeq())
			.toList();
	}
}
