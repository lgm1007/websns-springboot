package com.lgmpjt.websnsspringboot.adapter.out.entity;

import java.util.Optional;

public interface UserRepository  {
	Optional<User> findById(Long userSeq);

	User save(User user);

	void delete(User user);
}
