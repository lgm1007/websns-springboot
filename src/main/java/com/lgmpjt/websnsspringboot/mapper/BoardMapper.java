package com.lgmpjt.websnsspringboot.mapper;

import com.lgmpjt.websnsspringboot.adapter.out.persistence.entity.Board;
import com.lgmpjt.websnsspringboot.adapter.out.persistence.entity.Member;
import com.lgmpjt.websnsspringboot.application.port.in.dto.BoardCreateDto;
import com.lgmpjt.websnsspringboot.application.port.in.dto.BoardDto;
import com.lgmpjt.websnsspringboot.application.port.in.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BoardMapper {
	BoardMapper INSTANCE = Mappers.getMapper(BoardMapper.class);

	@Mapping(source = "user", target = "user", qualifiedByName = "userDtoToUser")
	Board createDtoToBoard(BoardCreateDto boardCreateDto);

	@Named("userDtoToUser")
	default Member userDtoToUser(UserDto userDto) {
		if ( userDto == null ) {
			return null;
		}

		Member.UserBuilder user = Member.builder();

		user.userSeq(userDto.getUserSeq() );
		user.userId( userDto.getUserId() );
		user.password( userDto.getPassword() );
		user.userName( userDto.getUserName() );
		user.userEmail( userDto.getUserEmail() );
		user.createdDate( userDto.getCreatedDate() );
		user.lastModifiedDate( userDto.getLastModifiedDate() );
		user.isAdmin( userDto.isAdmin() );
		user.isPrivate(userDto.isPrivate() );

		return user.build();
	}

	BoardDto boardToDto(Board board);

	List<BoardDto> boardsToDtos(List<Board> boards);
}
