package com.dengry.springbootshiro.service;

import com.dengry.springbootshiro.entity.Resource;
import com.dengry.springbootshiro.entity.Role;
import com.dengry.springbootshiro.entity.User;
import com.dengry.springbootshiro.valueObject.Node;
import com.dengry.springbootshiro.valueObject.Perm;

import java.util.List;
import java.util.Map;

public interface MyService {
    User findUserByUsername(String username);

    /**
     * 取得左侧导航树
     *
     * @return
     */
    List<com.dengry.springbootshiro.entity.Node> findLeftTree();

    /**
     * 角色不同，导航树不同(查询role_node表)
     *
     * @param roleId
     * @return
     */
    List<com.dengry.springbootshiro.entity.Node> findLeftTree(Integer roleId);

    List<Resource> findResesByRoleId(Integer roleId);
    List<Resource> findResesByNodeId(Integer nodeId);

    List<Integer> findResIdsByNodeId(Integer nodeId);

    List<Perm> findPermissiones(Integer[] resIds);

    Map<String, Object> findRoles(String name, Integer pageIndex, Integer pageSize);

    Map<String, Object> findUsers(String username, Integer pageIndex, Integer pageSize);

    Map<String, Object> findReses(String url, Integer pageIndex, Integer pageSize);

    List<Resource> findResByNode(Integer nodeId);

    com.dengry.springbootshiro.entity.Node findNodeById(Integer id);

    void addNode(Node node);

    void delNode(Integer id);

    void addRole(Role role);

    void delRolesByIds(Integer[] ids);

    void delUsersByIds(Integer[] ids);

    Role findRoleById(Integer id);

    void addUser(User user);

    User findUserById(Integer id);

    void updateUser(User user);

    void saveResource(Resource resource);

    void saveNode(com.dengry.springbootshiro.entity.Node node);

    void delResById(Integer id);

    List<Resource> findResources();
}
