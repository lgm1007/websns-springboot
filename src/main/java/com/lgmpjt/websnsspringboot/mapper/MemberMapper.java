package com.lgmpjt.websnsspringboot.mapper;

import com.lgmpjt.websnsspringboot.adapter.out.persistence.entity.Member;
import com.lgmpjt.websnsspringboot.application.port.in.dto.MemberCreateDto;
import com.lgmpjt.websnsspringboot.application.port.in.dto.MemberDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/*
componentModel = "spring": spring 컨테이너에 Bean 등록
unmappedTargetPolicy = ReportingPolicy.IGNORE: target class에 매핑되지 않는 필드가 있다면 null로 넣은 후 리포팅하지 않음
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MemberMapper {
	MemberMapper INSTANCE = Mappers.getMapper(MemberMapper.class);

	Member createDtoToMember(MemberCreateDto memberCreateDto);

	MemberDto toMemberDto(Member entity);
}
