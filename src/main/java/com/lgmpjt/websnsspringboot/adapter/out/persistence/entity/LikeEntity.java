package com.lgmpjt.websnsspringboot.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Likes")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LikeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long likeSeq;


	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "memberSeq")
	private Member member;

	@Column(insertable = false, updatable = false)
	private Long memberSeq;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "boardSeq")
	private Board board;

	@Column(insertable = false, updatable = false)
	private Long boardSeq;

	private LocalDateTime createdDate;
}
