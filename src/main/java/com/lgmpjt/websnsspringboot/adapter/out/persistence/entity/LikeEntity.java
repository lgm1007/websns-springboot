package com.lgmpjt.websnsspringboot.adapter.out.persistence.entity;

import com.lgmpjt.websnsspringboot.adapter.out.persistence.entity.id.LikeId;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Likes")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LikeEntity {
	@EmbeddedId
	private LikeId likeId;

	private LocalDateTime createdDate;
}
