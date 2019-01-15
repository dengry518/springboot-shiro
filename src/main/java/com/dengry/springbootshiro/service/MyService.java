package com.dengry.springbootshiro.service;

import com.dengry.springbootshiro.entity.User;
import com.dengry.springbootshiro.valueObject.Node;

import java.util.List;

public interface MyService {
    User findUserByUsername(String username);

    /**
     * 取得左侧导航树
     *
     * @return
     */
    List<Node> findLeftTree();
}
