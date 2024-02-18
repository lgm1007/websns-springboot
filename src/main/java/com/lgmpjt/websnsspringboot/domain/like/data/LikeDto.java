package com.lgmpjt.websnsspringboot.domain.like.data;

import lombok.*;
import org.springframework.util.Assert;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
public class LikeDto {
	private Long userSeq;
	private Long boardSeq;
	private LocalDateTime createdDate;

	public LikeDto(final Long userSeq, final Long boardSeq, final LocalDateTime createdDate) {
		Assert.notNull(userSeq, "좋아요 한 유저 정보는 필수입니다.");
		Assert.notNull(boardSeq, "좋아요 대상인 게시글 정보는 필수입니다.");
		this.userSeq = userSeq;
		this.boardSeq = boardSeq;
		this.createdDate = createdDate;
	}
}
