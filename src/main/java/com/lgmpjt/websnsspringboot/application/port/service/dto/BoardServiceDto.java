package com.lgmpjt.websnsspringboot.application.port.service.dto;

import com.lgmpjt.websnsspringboot.adapter.in.rest.request.BoardCreateRequest;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BoardServiceDto {
	@NotNull
	private final Long memberSeq;
	private final String content;
	@NotNull
	private final String boardImage;

	public static BoardServiceDto from(final BoardCreateRequest boardCreateRequest) {
		return BoardServiceDto.builder()
			.memberSeq(boardCreateRequest.getMemberSeq())
			.content(boardCreateRequest.getContent())
			.boardImage(boardCreateRequest.getBoardImage())
			.build();
	}
}
