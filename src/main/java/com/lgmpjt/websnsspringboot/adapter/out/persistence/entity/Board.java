package com.lgmpjt.websnsspringboot.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
@Table(name = "Board")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Board extends CommonEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long boardSeq;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "memberSeq")
	private Member member;

	@Column(insertable = false, updatable = false)
	private Long memberSeq;

	private String content;

	@NotNull
	private String boardImage;

	public void updateBoard(final String content, final String boardImage) {
		this.content = content;
		this.boardImage = boardImage;
	}
}
