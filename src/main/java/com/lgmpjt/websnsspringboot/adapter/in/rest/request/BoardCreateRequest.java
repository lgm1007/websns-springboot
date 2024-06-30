package com.lgmpjt.websnsspringboot.adapter.in.rest.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BoardCreateRequest {
	private final String content;
	@NotNull
	private final String boardImage;
	@NotNull
	private final Long memberSeq;

	public static BoardCreateRequest of(final String content,
											   final String boardImage,
											   final Long memberSeq) {
		return new BoardCreateRequest(content, boardImage, memberSeq);
	}
}
