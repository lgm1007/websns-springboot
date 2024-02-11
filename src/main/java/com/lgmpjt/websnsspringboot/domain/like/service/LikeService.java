package com.lgmpjt.websnsspringboot.domain.like.service;

import com.lgmpjt.websnsspringboot.domain.like.data.LikeDto;
import com.lgmpjt.websnsspringboot.domain.like.model.Likes;
import com.lgmpjt.websnsspringboot.mapper.like.LikeMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
