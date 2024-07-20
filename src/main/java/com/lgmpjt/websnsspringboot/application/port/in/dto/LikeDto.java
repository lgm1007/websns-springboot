package com.lgmpjt.websnsspringboot.application.port.in.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.util.Assert;

@Getter
@ToString
public class LikeDto {
	private final Long memberSeq;
	private final Long boardSeq;

	@Builder
	public LikeDto(final Long memberSeq, final Long boardSeq) {
		Assert.notNull(memberSeq, "좋아요 한 유저 정보는 필수입니다.");
		Assert.notNull(boardSeq, "좋아요 대상인 게시글 정보는 필수입니다.");
		this.memberSeq = memberSeq;
		this.boardSeq = boardSeq;
	}
}
