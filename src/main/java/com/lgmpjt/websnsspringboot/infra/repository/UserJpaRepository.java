package com.lgmpjt.websnsspringboot.infra.repository;

import com.lgmpjt.websnsspringboot.adapter.out.persistence.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<Member, Long> {
}
