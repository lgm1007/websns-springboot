package com.lgmpjt.websnsspringboot.domain.follow.data;

import lombok.*;
import org.springframework.util.Assert;

@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
public class FollowCreateDto {
	private Long fromFollow;
	private Long toFollow;

	public FollowCreateDto(Long fromFollow, Long toFollow) {
		Assert.notNull(fromFollow, "팔로우하는 유저 정보는 필수입니다.");
		Assert.notNull(toFollow, "팔로우하려는 대상 유저 정보는 필수입니다.");
		this.fromFollow = fromFollow;
		this.toFollow = toFollow;
	}
}
