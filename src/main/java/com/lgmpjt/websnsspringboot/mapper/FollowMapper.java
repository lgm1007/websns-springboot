package com.lgmpjt.websnsspringboot.mapper;

import com.lgmpjt.websnsspringboot.application.port.in.dto.FollowDto;
import com.lgmpjt.websnsspringboot.adapter.out.entity.Follow;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FollowMapper {
	FollowMapper INSTANCE = Mappers.getMapper(FollowMapper.class);

	List<FollowDto> followToSearchDtos(List<Follow> follow);
}
