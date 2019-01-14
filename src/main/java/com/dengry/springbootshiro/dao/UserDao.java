package com.dengry.springbootshiro.dao;

import com.dengry.springbootshiro.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {
    User findUserByUsername(String username);
}
