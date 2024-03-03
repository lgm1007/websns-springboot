package com.lgmpjt.websnsspringboot.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "User")
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userSeq;

	private String userId;

	private String password;

	private String userName;

	private String userEmail;

	private LocalDateTime createdDate;

	private LocalDateTime lastModifiedDate;

	private boolean isAdmin;

	private boolean isPrivate;

}
