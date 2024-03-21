package com.lgmpjt.websnsspringboot.mapper;

import com.lgmpjt.websnsspringboot.adapter.out.persistence.entity.Board;
import com.lgmpjt.websnsspringboot.adapter.out.persistence.entity.Member;
import com.lgmpjt.websnsspringboot.application.port.in.dto.BoardCreateDto;
import com.lgmpjt.websnsspringboot.application.port.in.dto.BoardDto;
import com.lgmpjt.websnsspringboot.application.port.in.dto.MemberDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BoardMapper {
	BoardMapper INSTANCE = Mappers.getMapper(BoardMapper.class);

	@Mapping(source = "member", target = "member", qualifiedByName = "memberDtoToMember")
	Board createDtoToBoard(BoardCreateDto boardCreateDto);

	@Named("memberDtoToMember")
	default Member memberDtoToMember(MemberDto memberDto) {
		if ( memberDto == null ) {
			return null;
		}

		Member.MemberBuilder builder = Member.builder();

		builder.memberSeq(memberDto.getMemberSeq() );
		builder.memberId( memberDto.getMemberId() );
		builder.password( memberDto.getPassword() );
		builder.memberName( memberDto.getMemberName() );
		builder.email( memberDto.getEmail() );
		builder.createdDate( memberDto.getCreatedDate() );
		builder.lastModifiedDate( memberDto.getLastModifiedDate() );
		builder.isAdmin( memberDto.isAdmin() );
		builder.isPrivate(memberDto.isPrivate() );

		return builder.build();
	}

	BoardDto boardToDto(Board board);

	List<BoardDto> boardsToDtos(List<Board> boards);
}
