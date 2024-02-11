package com.lgmpjt.websnsspringboot.domain.like.data;

import com.lgmpjt.websnsspringboot.domain.board.data.BoardDto;
import com.lgmpjt.websnsspringboot.domain.user.data.UserDto;
import lombok.*;
import org.springframework.util.Assert;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
public class LikeDto {
	private UserDto user;
	private BoardDto board;
	private LocalDateTime createdDate;

	public LikeDto(final UserDto user, final BoardDto board, final LocalDateTime createdDate) {
		Assert.notNull(user, "좋아요 한 유저 정보는 필수입니다.");
		Assert.notNull(board, "좋아요 대상인 게시글 정보는 필수입니다.");
		this.user = user;
		this.board = board;
		this.createdDate = createdDate;
	}
}
