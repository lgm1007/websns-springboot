package com.lgmpjt.websnsspringboot.adapter.out.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Likes")
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Likes {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long likeSeq;


	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userSeq")
	private User user;

	@Column(insertable = false, updatable = false)
	private Long userSeq;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "boardSeq")
	private Board board;

	@Column(insertable = false, updatable = false)
	private Long boardSeq;

	private LocalDateTime createdDate;
}
