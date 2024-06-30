package com.lgmpjt.websnsspringboot.application.port.in;

import com.lgmpjt.websnsspringboot.application.port.in.dto.FollowDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface FollowSearchUseCase {

	List<FollowDto> findAllFollowingByMember(final Long memberSeq);

	List<FollowDto> findAllFollowerByMember(final Long memberSeq);
}
