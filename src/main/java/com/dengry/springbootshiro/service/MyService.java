package com.dengry.springbootshiro.service;

import com.dengry.springbootshiro.entity.User;

public interface MyService {
    User findUserByUsername(String username);
}
