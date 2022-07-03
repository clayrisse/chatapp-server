package com.chatapp.repository;

import com.chatapp.model.UChatter;
import com.chatapp.model.User;

import java.util.Optional;

public interface UChatterRepository {
    Optional<UChatter> findByUsername(String username);
}
