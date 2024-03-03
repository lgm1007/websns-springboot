package com.lgmpjt.websnsspringboot.adapter.out.persistence.repository;

import com.lgmpjt.websnsspringboot.adapter.out.persistence.entity.User;

import java.util.Optional;

public interface UserRepository  {
	Optional<User> findById(Long userSeq);

	User save(User user);

	void delete(User user);
}
