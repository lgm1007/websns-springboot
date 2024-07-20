package com.lgmpjt.websnsspringboot.application.port.in.dto;

import com.lgmpjt.websnsspringboot.adapter.out.persistence.entity.Follow;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.util.Assert;

@ToString
@Getter
public class FollowDto {
	private final Long followSeq;
	private final Long fromFollow;
	private final Long toFollow;

	@Builder
	public FollowDto(final Long followSeq, final Long fromFollow, final Long toFollow) {
		Assert.notNull(followSeq, "Follow SEQ 값은 필수입니다.");
		Assert.notNull(fromFollow, "팔로우하는 유저 정보는 필수입니다.");
		Assert.notNull(toFollow, "팔로우하려는 대상 유저 정보는 필수입니다.");
		this.followSeq = followSeq;
		this.fromFollow = fromFollow;
		this.toFollow = toFollow;
	}

	public static FollowDto from(final Follow follow) {
		return FollowDto.builder()
			.followSeq(follow.getFollowSeq())
			.fromFollow(follow.getFromFollow())
			.toFollow(follow.getToFollow())
			.build();
	}
}
