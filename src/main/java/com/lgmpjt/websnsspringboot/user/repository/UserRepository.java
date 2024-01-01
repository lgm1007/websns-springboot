package com.lgmpjt.websnsspringboot.user.repository;

import com.lgmpjt.websnsspringboot.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
