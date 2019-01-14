package com.dengry.springbootshiro.service;

import com.dengry.springbootshiro.dao.UserDao;
import com.dengry.springbootshiro.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class MyServiceImpl implements MyService {
    @Autowired
    UserDao userDao;

    @Override
    public User findUserByUsername(String username) {
        return userDao.findUserByUsername(username);
    }
}
