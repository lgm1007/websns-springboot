package com.lgmpjt.websnsspringboot.domain.like.service;

import com.lgmpjt.websnsspringboot.domain.like.model.Likes;

public interface LikePort {
	Likes save(final Likes like);
}
