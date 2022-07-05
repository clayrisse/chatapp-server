package com.chatapp.repository;

import com.chatapp.model.UChatter;
import com.chatapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UChatterRepository extends JpaRepository<UChatter, Long> {
    Optional<UChatter> findByUsername(String username);
}
