package com.lgmpjt.websnsspringboot.adapter.out;

import com.lgmpjt.websnsspringboot.adapter.out.persistence.entity.LikeEntity;
import com.lgmpjt.websnsspringboot.adapter.out.persistence.repository.LikeRepository;
import com.lgmpjt.websnsspringboot.application.port.out.LikePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class LikeAdapter implements LikePort {

	private final LikeRepository likeRepository;

	@Override
	public LikeEntity save(LikeEntity like) {
		return likeRepository.save(like);
	}

	@Override
	public LikeEntity findByMemberSeqAndBoardSeq(Long memberSeq, Long boardSeq) {
		return likeRepository.findByMemberSeqAndBoardSeq(memberSeq, boardSeq);
	}

	@Override
	public void delete(LikeEntity like) {
		likeRepository.delete(like);
	}

	@Override
	public List<LikeEntity> findAllByMemberSeq(Long memberSeq) {
		return likeRepository.findAllByMemberSeq(memberSeq);
	}
}
