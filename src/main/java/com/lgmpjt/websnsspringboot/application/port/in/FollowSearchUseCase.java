package com.lgmpjt.websnsspringboot.application.port.in;

import com.lgmpjt.websnsspringboot.application.port.in.dto.FollowDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface FollowSearchUseCase {
	@Transactional(readOnly = true)
	List<FollowDto> findAllFollowingByMember(final Long memberSeq);

	@Transactional(readOnly = true)
	List<FollowDto> findAllFollowerByMember(final Long memberSeq);
}
