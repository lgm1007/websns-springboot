package com.lgmpjt.websnsspringboot.mapper;

import com.lgmpjt.websnsspringboot.adapter.in.rest.request.BoardCreateRequest;
import com.lgmpjt.websnsspringboot.adapter.out.persistence.entity.Board;
import com.lgmpjt.websnsspringboot.application.port.in.dto.BoardDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BoardMapper {
	BoardMapper INSTANCE = Mappers.getMapper(BoardMapper.class);

	Board createRequestToBoard(BoardCreateRequest boardCreateRequest);

	BoardDto boardToDto(Board board);

	List<BoardDto> boardsToDtos(List<Board> boards);
}
