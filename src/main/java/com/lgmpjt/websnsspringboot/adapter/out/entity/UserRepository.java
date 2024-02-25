package com.lgmpjt.websnsspringboot.adapter.out.entity;

import com.lgmpjt.websnsspringboot.adapter.out.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
