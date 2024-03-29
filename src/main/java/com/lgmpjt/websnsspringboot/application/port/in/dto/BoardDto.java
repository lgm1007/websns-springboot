package com.lgmpjt.websnsspringboot.application.port.in.dto;

import lombok.*;
import org.springframework.util.Assert;

import java.time.LocalDateTime;

@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardDto {
	private Long boardSeq;
	private MemberDto member;
	private String content;
	private String boardImage;
	private LocalDateTime createdDate;
	private LocalDateTime lastModifiedDate;

	public BoardDto(final Long boardSeq, final MemberDto member, final String content, final String boardImage, final LocalDateTime createdDate) {
		Assert.notNull(boardSeq, "게시물 SEQ 정보는 필수입니다.");
		Assert.notNull(member, "게시물을 작성한 유저 정보는 필수입니다.");
		Assert.hasText(boardImage, "게시물의 이미지는 필수입니다.");
		this.boardSeq = boardSeq;
		this.member = member;
		this.content = content;
		this.boardImage = boardImage;
		this.createdDate = createdDate;
		this.lastModifiedDate = LocalDateTime.now();
	}
}
