package com.lgmpjt.websnsspringboot.application.port.in;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface LikeSearchUseCase {

	List<Long> findAllLikeBoardSeqByMemberSeq(final Long memberSeq);
}
