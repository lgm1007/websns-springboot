package com.lgmpjt.websnsspringboot.domain.board.model;

import com.lgmpjt.websnsspringboot.domain.user.model.User;
import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDateTime;

@Entity
@Table(name = "Board")
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long boardSeq;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userSeq")
	private User user;

	private String content;

	@NotNull
	private String boardImage;

	private LocalDateTime createdDate;

	private LocalDateTime lastModifiedDate;
}
