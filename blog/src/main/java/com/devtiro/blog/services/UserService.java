package com.devtiro.blog.services;

import com.devtiro.blog.domain.User;

import java.util.UUID;

public interface UserService {

    User getUserById(UUID id);
}
