package com.lgmpjt.websnsspringboot.mapper;

import com.lgmpjt.websnsspringboot.adapter.out.entity.LikeEntity;
import com.lgmpjt.websnsspringboot.application.port.in.dto.LikeDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LikeMapper {
	LikeMapper INSTANCE = Mappers.getMapper(LikeMapper.class);

	LikeEntity dtoToLike(LikeDto likeDto);

	LikeDto likeToDto(LikeEntity like);
}
