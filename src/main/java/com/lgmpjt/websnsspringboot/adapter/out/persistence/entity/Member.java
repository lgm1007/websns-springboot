package com.lgmpjt.websnsspringboot.adapter.out.persistence.entity;

import com.lgmpjt.websnsspringboot.application.port.service.dto.MemberServiceDto;
import com.lgmpjt.websnsspringboot.utils.SHA256;
import jakarta.persistence.*;
import lombok.*;

import java.security.NoSuchAlgorithmException;

@Entity
@Table(name = "Member")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Member extends CommonEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long memberSeq;

	private String memberId;

	private String password;

	private String memberName;

	private String email;

	private boolean isAdmin;

	private boolean isPrivate;

	private boolean deleted;

	public void updateMember(final String password, final String memberName, final String email) throws NoSuchAlgorithmException {
		try {
			this.password = SHA256.encrypt(password);
			this.memberName = memberName;
			this.email = email;
		} catch (Exception e) {
			throw new NoSuchAlgorithmException(e.getMessage());
		}
	}

	public static Member from(final MemberServiceDto memberServiceDto) {
		return Member.builder()
			.memberId(memberServiceDto.getMemberId())
			.password(memberServiceDto.getPassword())
			.memberName(memberServiceDto.getMemberName())
			.email(memberServiceDto.getEmail())
			.isAdmin(memberServiceDto.isAdmin())
			.isPrivate(memberServiceDto.isPrivate())
			.build();
	}
}
