package com.lgmpjt.websnsspringboot.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Follow")
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Follow {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long followSeq;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fromFollow")
	private Member from;

	@Column(insertable = false, updatable = false)
	private Long fromFollow;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "toFollow")
	private Member to;

	@Column(insertable = false, updatable = false)
	private Long toFollow;
}
