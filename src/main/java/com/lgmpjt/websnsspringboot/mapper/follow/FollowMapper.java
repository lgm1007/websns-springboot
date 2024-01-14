package com.lgmpjt.websnsspringboot.mapper.follow;

import com.lgmpjt.websnsspringboot.domain.follow.data.FollowCreateDto;
import com.lgmpjt.websnsspringboot.domain.follow.model.Follow;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FollowMapper {
	FollowMapper INSTANCE = Mappers.getMapper(FollowMapper.class);

	Follow createDtoToFollow(FollowCreateDto followCreateDto);
}
