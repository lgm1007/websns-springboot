package com.lgmpjt.websnsspringboot.application.port.in.dto;

import com.lgmpjt.websnsspringboot.adapter.out.persistence.entity.Board;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.util.Assert;

@ToString
@Getter
public class BoardDto {
	private final Long boardSeq;
	private final Long memberSeq;
	private final String content;
	private final String boardImage;

	@Builder
	public BoardDto(final Long boardSeq, final Long memberSeq, final String content, final String boardImage) {
		Assert.notNull(boardSeq, "게시물 SEQ 정보는 필수입니다.");
		Assert.notNull(memberSeq, "게시물을 작성한 유저 정보는 필수입니다.");
		this.boardSeq = boardSeq;
		this.memberSeq = memberSeq;
		this.content = content;
		this.boardImage = boardImage;
	}

	public static BoardDto from(final Board board) {
		return BoardDto.builder()
			.boardSeq(board.getBoardSeq())
			.memberSeq(board.getMemberSeq())
			.content(board.getContent())
			.boardImage(board.getBoardImage())
			.build();
	}
}
