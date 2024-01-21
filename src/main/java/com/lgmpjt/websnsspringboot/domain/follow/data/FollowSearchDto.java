package com.lgmpjt.websnsspringboot.domain.follow.data;

import lombok.*;
import org.springframework.util.Assert;

@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
public class FollowSearchDto {
	private Long followSeq;
	private Long fromFollow;
	private Long toFollow;

	public FollowSearchDto(final Long followSeq, final Long fromFollow, final Long toFollow) {
		Assert.notNull(followSeq, "Follow SEQ 값은 필수입니다.");
		Assert.notNull(fromFollow, "팔로우하는 유저 정보는 필수입니다.");
		Assert.notNull(toFollow, "팔로우하려는 대상 유저 정보는 필수입니다.");
		this.followSeq = followSeq;
		this.fromFollow = fromFollow;
		this.toFollow = toFollow;
	}
}
