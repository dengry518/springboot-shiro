package com.dengry.springbootshiro.service;

import com.dengry.springbootshiro.entity.Role;
import com.dengry.springbootshiro.entity.User;
import com.dengry.springbootshiro.valueObject.Node;

import java.util.List;
import java.util.Map;

public interface MyService {
    User findUserByUsername(String username);

    /**
     * 取得左侧导航树
     *
     * @return
     */
    List<Node> findLeftTree();


    Map<String, Object> findRoles(String name, Integer pageIndex, Integer pageSize);

    void addNode(Node node);

    void delNode(Integer id);

    void addRole(Role role);

    void delRoleByIds(Integer[] ids);

    Role findRoleById(Integer id);
}
