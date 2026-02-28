package com.sample.project.repository;

import com.sample.project.models.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface user_repo extends JpaRepository<user, Long> {

    boolean existsByEmail(String email);

    user findByUsername(String username);
}
