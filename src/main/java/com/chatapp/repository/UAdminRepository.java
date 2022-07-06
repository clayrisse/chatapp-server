package com.chatapp.repository;

import com.chatapp.model.UAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UAdminRepository extends JpaRepository<UAdmin, Long> {
}
