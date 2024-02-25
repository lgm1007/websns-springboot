package com.lgmpjt.websnsspringboot.application.port.in;

import com.lgmpjt.websnsspringboot.application.port.in.dto.FollowDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface FollowSearchUseCase {
	@Transactional(readOnly = true)
	List<FollowDto> findAllFollowingByUser(final Long userSeq);

	@Transactional(readOnly = true)
	List<FollowDto> findAllFollowerByUser(final Long userSeq);
}
