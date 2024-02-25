package com.lgmpjt.websnsspringboot.mapper.like;

import com.lgmpjt.websnsspringboot.domain.like.data.LikeDto;
import com.lgmpjt.websnsspringboot.adapter.out.entity.Likes;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LikeMapper {
	LikeMapper INSTANCE = Mappers.getMapper(LikeMapper.class);

	Likes dtoToLike(LikeDto likeDto);

	LikeDto likeToDto(Likes like);
}
