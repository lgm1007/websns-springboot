package com.lgmpjt.websnsspringboot.mapper.board;

import com.lgmpjt.websnsspringboot.domain.board.data.BoardCreateDto;
import com.lgmpjt.websnsspringboot.domain.board.data.BoardDto;
import com.lgmpjt.websnsspringboot.domain.board.model.Board;
import com.lgmpjt.websnsspringboot.domain.user.data.UserSearchUpdateDto;
import com.lgmpjt.websnsspringboot.domain.user.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BoardMapper {
	BoardMapper INSTANCE = Mappers.getMapper(BoardMapper.class);

	@Mapping(source = "user", target = "user", qualifiedByName = "userDtoToUser")
	Board createDtoToBoard(BoardCreateDto boardCreateDto);

	@Named("userDtoToUser")
	default User userDtoToUser(UserSearchUpdateDto userDto) {
		if ( userDto == null ) {
			return null;
		}

		User.UserBuilder user = User.builder();

		user.userSeq(userDto.getUserSeq() );
		user.userId( userDto.getUserId() );
		user.password( userDto.getPassword() );
		user.userName( userDto.getUserName() );
		user.userEmail( userDto.getUserEmail() );
		user.createdDate( userDto.getCreatedDate() );
		user.lastModifiedDate( userDto.getLastModifiedDate() );
		user.admin( userDto.isAdmin() );

		return user.build();
	}

	BoardDto boardToDto(Board board);
}
