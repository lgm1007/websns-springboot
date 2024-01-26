package com.lgmpjt.websnsspringboot.domain.board.data;

import com.lgmpjt.websnsspringboot.domain.user.data.UserSearchUpdateDto;
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
	private UserSearchUpdateDto user;
	private String content;
	private String boardImage;
	private LocalDateTime createdDate;
	private LocalDateTime lastModifiedDate;

	public BoardDto(final Long boardSeq, final UserSearchUpdateDto user, final String content, final String boardImage) {
		Assert.notNull(boardSeq, "게시물 SEQ 정보는 필수입니다.");
		Assert.notNull(user, "게시물을 작성한 유저 정보는 필수입니다.");
		Assert.hasText(boardImage, "게시물의 이미지는 필수입니다.");
		this.boardSeq = boardSeq;
		this.user = user;
		this.content = content;
		this.boardImage = boardImage;
		this.lastModifiedDate = LocalDateTime.now();
	}
}