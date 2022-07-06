package com.chatapp.repository;

import com.chatapp.model.UChatter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UChatterRepository extends JpaRepository<UChatter, Long> {
    Optional<UChatter> findByUsername(String username);
}
