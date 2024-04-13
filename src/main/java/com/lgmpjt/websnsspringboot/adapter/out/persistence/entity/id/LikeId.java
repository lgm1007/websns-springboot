package com.lgmpjt.websnsspringboot.adapter.out.persistence.entity.id;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LikeId implements Serializable {
	@Column(name = "memberSeq")
	private Long memberSeq;

	@Column(name = "boardSeq")
	private Long boardSeq;

	@Builder
	public LikeId(Long memberSeq, Long boardSeq) {
		this.memberSeq = memberSeq;
		this.boardSeq = boardSeq;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof LikeId))
			return false;

		return Objects.equals(this.memberSeq, ((LikeId) obj).getMemberSeq()) &&
				Objects.equals(this.boardSeq, ((LikeId) obj).getBoardSeq());
	}

	@Override
	public int hashCode() {
		return Objects.hash(memberSeq, boardSeq);
	}
}
