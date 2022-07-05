package com.chatapp.repository;

import com.chatapp.model.UAdmin;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UAdminRepository extends JpaRepository<UAdmin, Long> {
}
