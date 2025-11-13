package com.naing.blog.services;

import java.util.UUID;

import com.naing.blog.domain.entities.User;

public interface UserService {
    User getUserById(UUID id);
}
