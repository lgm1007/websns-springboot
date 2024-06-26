package com.lgmpjt.websnsspringboot.application.port.in.dto;

import lombok.*;
import org.springframework.util.Assert;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class LikeDto {
	private Long memberSeq;
	private Long boardSeq;

	@Builder
	public LikeDto(final Long memberSeq, final Long boardSeq) {
		Assert.notNull(memberSeq, "좋아요 한 유저 정보는 필수입니다.");
		Assert.notNull(boardSeq, "좋아요 대상인 게시글 정보는 필수입니다.");
		this.memberSeq = memberSeq;
		this.boardSeq = boardSeq;
	}
}
