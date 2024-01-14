package com.lgmpjt.websnsspringboot.domain.user.repository;

import com.lgmpjt.websnsspringboot.domain.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
