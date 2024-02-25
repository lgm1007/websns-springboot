package com.lgmpjt.websnsspringboot.application.port.in.dto;

import lombok.*;
import org.springframework.util.Assert;

import java.time.LocalDateTime;

@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
public class BoardCreateDto {
	private UserDto user;
	private String content;
	private String boardImage;
	private LocalDateTime createdDate;

	public BoardCreateDto(UserDto user, String content, String boardImage, LocalDateTime createdDate) {
		Assert.notNull(user, "게시물을 작성한 유저 정보는 필수입니다.");
		Assert.hasText(boardImage, "게시물의 이미지는 필수입니다.");
		this.user = user;
		this.content = content;
		this.boardImage = boardImage;
		this.createdDate = createdDate;
	}
}
