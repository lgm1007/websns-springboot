package com.lgmpjt.websnsspringboot.domain.follow.model;

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

	private Long fromFollow;

	private Long toFollow;
}
