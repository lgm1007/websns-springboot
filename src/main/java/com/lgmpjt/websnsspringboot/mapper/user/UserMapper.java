package com.lgmpjt.websnsspringboot.mapper.user;

import com.lgmpjt.websnsspringboot.user.data.UserCreateDto;
import com.lgmpjt.websnsspringboot.user.data.UserSearchUpdateDto;
import com.lgmpjt.websnsspringboot.user.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/*
componentModel = "spring": spring 컨테이너에 Bean 등록
unmappedTargetPolicy = ReportingPolicy.IGNORE: target class에 매핑되지 않는 필드가 있다면 null로 넣은 후 리포팅하지 않음
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

	User createDtoToUser(UserCreateDto userCreateDto);

	UserSearchUpdateDto toUserSearchDto(User entity);
}
