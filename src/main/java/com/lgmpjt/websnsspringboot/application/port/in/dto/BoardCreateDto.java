package com.lgmpjt.websnsspringboot.application.port.in.dto;

import lombok.*;
import org.springframework.util.Assert;

import java.time.LocalDateTime;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class BoardCreateDto {
	private MemberDto member;
	private String content;
	private String boardImage;
	private LocalDateTime createdDate;

	@Builder
	public BoardCreateDto(MemberDto member, String content, String boardImage, LocalDateTime createdDate) {
		Assert.notNull(member, "게시물을 작성한 유저 정보는 필수입니다.");
		Assert.hasText(boardImage, "게시물의 이미지는 필수입니다.");
		this.member = member;
		this.content = content;
		this.boardImage = boardImage;
		this.createdDate = createdDate;
	}
}
