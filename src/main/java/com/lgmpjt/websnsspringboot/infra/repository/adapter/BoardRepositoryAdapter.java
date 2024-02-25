package com.lgmpjt.websnsspringboot.infra.repository.adapter;

import com.lgmpjt.websnsspringboot.adapter.out.entity.Board;
import com.lgmpjt.websnsspringboot.adapter.out.entity.BoardRepository;
import com.lgmpjt.websnsspringboot.infra.repository.BoardJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardRepositoryAdapter implements BoardRepository {

	private final BoardJpaRepository jpaRepository;

	@Override
	public List<Board> findAllByUserSeq(Long userSeq) {
		return jpaRepository.findAllByUserSeq(userSeq);
	}
}
