package com.lgmpjt.websnsspringboot.mapper.follow;

import com.lgmpjt.websnsspringboot.domain.follow.data.FollowSearchDto;
import com.lgmpjt.websnsspringboot.adapter.out.entity.Follow;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FollowMapper {
	FollowMapper INSTANCE = Mappers.getMapper(FollowMapper.class);

	List<FollowSearchDto> followToSearchDtos(List<Follow> follow);
}
