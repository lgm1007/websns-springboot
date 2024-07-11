package com.lgmpjt.websnsspringboot.adapter.in.rest.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BoardCreateRequest {
	@NotNull
	private final Long memberSeq;
	private final String content;
	@NotNull
	private final String boardImage;

	public static BoardCreateRequest of(final Long memberSeq,
										final String content,
										final String boardImage) {
		return new BoardCreateRequest(memberSeq, content, boardImage);
	}
}
