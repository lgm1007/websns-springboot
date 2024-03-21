package com.lgmpjt.websnsspringboot.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Member")
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long memberSeq;

	private String memberId;

	private String password;

	private String memberName;

	private String email;

	private LocalDateTime createdDate;

	private LocalDateTime lastModifiedDate;

	private boolean isAdmin;

	private boolean isPrivate;

	private boolean deleted;

}
